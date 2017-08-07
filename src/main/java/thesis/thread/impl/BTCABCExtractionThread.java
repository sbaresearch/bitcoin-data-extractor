package thesis.thread.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.exception.ServiceException;
import thesis.model.Block;

@Profile("btcabc")
@Component
public class BTCABCExtractionThread extends AbstractExtractionThread {

   /**
    @Override
    protected Block getBlock(String hash) throws ServiceException {
        return rpcBlockRequestService.getBlockByHash(hash);
    }
    */
}
