package thesis.http.util;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * This class creates/sends Http POST requests and handles the responses
 *
 * @author Alexei
 */
@Component
public class HttpJsonClient {

    @Value("${jsonrpc.rpcuser}")
    private String username;

    @Value("${jsonrpc.rpcpassword}")
    private String password;

    @Value("${jsonrpc.host}")
    private String host;

    @Value("${jsonrpc.port}")
    private int port;
    private HttpClient httpClient;
    private HttpClientContext context;


    public HttpJsonClient() {
    }


    @PostConstruct
    private void init() {
        HttpHost targetHost = new HttpHost(host, port, "http");

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        // Add AuthCache to the execution context
        context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);

        httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Execute HTTP (POST) Request
     *
     * @param jsonRequest - JSON Request String, which is to be sent to the server
     * @return - JSONArray returned from server
     */
    public String execute(JSONObject jsonRequest) throws IOException {

        HttpResponse response;
        StringEntity finalRequest;

        finalRequest = new StringEntity(jsonRequest.toJSONString());

        HttpPost httpPost = new HttpPost(host + ":" + port);
        httpPost.addHeader("content-type", "text/plain");
        httpPost.setEntity(finalRequest);

        response = httpClient.execute(httpPost, context);

        String responseString = EntityUtils.toString(response.getEntity(),
                "UTF-8");

        return responseString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
