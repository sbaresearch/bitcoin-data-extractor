package thesis.http.impl;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;
import thesis.http.util.HttpJsonClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Profile({"ltc"})
@Service
public class LTCBlockRequestServiceImpl extends AbstractBlockRequestServiceImpl {

    @Override
    public Integer getCurrentBlockHeight() throws ServiceException {

        int currentBlockHeight;
        // create first part of JSON request object
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("id", "block_height_request");
        jsonRequest.put("method", "getblockcount");
        // add parameters to json request
        JSONArray params = new JSONArray();
        jsonRequest.put("params", params);
        // execute json request
        String response;
        try {
            response = httpJsonClient.execute(jsonRequest);

            // parse json object
            Object resultObject = JSONValue.parse(response);

            JSONObject jsonRoot = (JSONObject) resultObject;

            // retrieve blockheight from json object
            currentBlockHeight = Integer.parseInt(jsonRoot.get("result")
                    .toString());

        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        return currentBlockHeight;
    }


    @Override
    public List<BlockDto> getBlockHashes(Integer count, String hash) throws ServiceException {


        String restUrl = url + "/headers/" + count + "/" + hash + ".json";

        List<BlockDto> hashArray = Arrays.asList(restTemplate.getForObject(restUrl, BlockDto[].class));

        logger.debug(restUrl);

        return hashArray;
    }
}
