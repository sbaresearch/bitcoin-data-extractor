package thesis.http.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import thesis.converter.impl.BlockConverter;
import thesis.dto.BlockDto;
import thesis.dto.ChainInfosDto;
import thesis.exception.ServiceException;
import thesis.http.BlockRequestService;
import thesis.http.util.HttpJsonClient;
import thesis.model.Block;
import thesis.model.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class AbstractBlockRequestServiceImpl implements BlockRequestService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RestTemplate restTemplate;

    @Autowired
    protected HttpJsonClient httpJsonClient;

    @Autowired
    protected BlockConverter converter;


    @Value("${rest.url}")
    protected String url;

    public AbstractBlockRequestServiceImpl() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    @Override
    public Integer getCurrentBlockHeight() throws ServiceException {


        String restUrl = url + "/chaininfo.json";
        ChainInfosDto chainInfosDto = restTemplate.getForObject(restUrl, ChainInfosDto.class);

        logger.debug(restUrl);

        return chainInfosDto.getBlocks();
    }

    @Override
    public Block getBlockByHash(String hash) throws ServiceException {


        String restUrl = url + "/block/" + hash + ".json";
        BlockDto blockDto = restTemplate.getForObject(restUrl, BlockDto.class);

        logger.debug(restUrl);

        return converter.toEntity(blockDto);
    }

    @Override
    public List<BlockDto> getBlockHashes(Integer count, String hash) throws ServiceException {

        String restUrl = url + "/headers/" + count + "/" + hash + ".json";

        List<BlockDto> hashArray = Arrays.asList(restTemplate.getForObject(restUrl, BlockDto[].class));

        logger.debug(restUrl);

        return hashArray;
    }

    @Override
    public List<BlockDto> getBlockHashesByHeight(int start, int end) throws ServiceException {

        List<BlockDto> blockDtos = new ArrayList<>();
        // create first part of JSON request object
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("id", "block_request");
        jsonRequest.put("method", "getblockhash");

        // execute json request
        String response;

        for(int height = start; height <= end; height++){

            // add parameters to json request
            JSONArray params = new JSONArray();
            params.add(0, height);
            jsonRequest.put("params", params);
            try {
                response = httpJsonClient.execute(jsonRequest);

                // parse json object
                Object resultObject = JSONValue.parse(response);

                JSONObject jsonRoot = (JSONObject) resultObject;

                // retrieve hash from json object
                String hash = jsonRoot.get("result").toString();

                blockDtos.add(new BlockDto(hash));

            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        }

        return blockDtos;
    }


}
