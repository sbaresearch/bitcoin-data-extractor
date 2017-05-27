package thesis.thread.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;

import java.util.List;

@Profile("myriad")
@Component
public class MyriadExtractionThread extends AbstractExtractionThread {

    @Override
    protected List<BlockDto> retrieveBlockHashes(Integer blockQuerySize, String blockhash, int blockHeight) throws ServiceException {
        return rpcBlockRequestService.getBlockHashesByHeight(blockHeight, blockHeight + blockQuerySize);
    }
}
