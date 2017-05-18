package thesis.http.impl;


import org.bouncycastle.util.encoders.Hex;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;
import thesis.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Profile({"doge", "myriad"})
@Service
public class DogeBlockRequestServiceImpl extends AbstractBlockRequestServiceImpl {

    @Override
    public List<BlockDto> getBlockHashes(Integer count, String hash) throws ServiceException {


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
