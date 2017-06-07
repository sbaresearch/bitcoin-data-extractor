package thesis.http.impl;

import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import thesis.converter.impl.BlockConverter;
import thesis.dto.BlockDto;
import thesis.dto.BlockDtoTxFree;
import thesis.dto.ChainInfosDto;
import thesis.exception.ServiceException;
import thesis.http.RESTBlockRequestService;
import thesis.model.Block;
import thesis.util.Utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class RESTBlockRequestServiceImpl implements RESTBlockRequestService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RestTemplate restTemplate;

    @Autowired
    protected BlockConverter converter;


    @Value("${rest.url}")
    protected String url;

    public RESTBlockRequestServiceImpl() {
        this.restTemplate = new RestTemplate();
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

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        BlockDto blockDto = restTemplate.getForObject(restUrl, BlockDto.class);

        logger.debug(restUrl);

        return converter.toEntity(blockDto);
    }

    @Override
    public BlockDtoTxFree getBlockByHashNoTxDetails(String hash) throws ServiceException {


        String restUrl = url + "/block/notxdetails/" + hash + ".json";
        BlockDtoTxFree blockDto = restTemplate.getForObject(restUrl, BlockDtoTxFree.class);

        logger.debug(restUrl);

        return blockDto;
    }

    @Override
    public List<BlockDto> getBlockHashes(Integer count, String hash) throws ServiceException {

        String restUrl = url + "/headers/" + count + "/" + hash + ".json";

        List<BlockDto> hashArray = Arrays.asList(restTemplate.getForObject(restUrl, BlockDto[].class));

        logger.debug(restUrl);

        return hashArray;
    }

    @Override
    public List<BlockDto> getBlockHashesHex(Integer count, String hash) throws ServiceException {


        String restUrl = url + "/headers/" + count + "/" + hash + ".hex";

        List<BlockDto> hashArray;

        hashArray = hexToBlockDto(restTemplate.getForObject(restUrl, String.class));

        logger.debug(restUrl);

        return hashArray;
    }


    public List<BlockDto> hexToBlockDto(String hex) throws ServiceException {
        List<BlockDto> blockDtos = new ArrayList<>();

        hex = hex.replace("\n", "").replace("\r", "");
        List<String> hexVals = new ArrayList<>();
        int index = 0;
        while (index < hex.length()) {
            String hex_header = hex.substring(index, Math.min(index + 160, hex.length()));

            /**
             * skip newline characters
             */
            if (hex_header.length() != 160) {
                continue;
            }
            hexVals.add(hex_header);
            byte[] bin_header = Hex.decode(hex_header);

            /*
            * check if merged mined or not, i.e. block count > 371337,
            * or hash = 60323982f9c5ff1b5a954eac9dc1269352835f47c2c5222691d80f0d50dcf053
             */
            String blockhash = Hex.toHexString(Utils.doubleSha256Hash(bin_header));
            blockDtos.add(new BlockDto(blockhash));


            index += 160;
        }

        return blockDtos;
    }
}
