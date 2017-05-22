package thesis.thread.impl;

import com.codahale.metrics.Timer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;

@Profile({"ltc"})
@Component
public class LTCExtractionThread extends AbstractExtractionThread {

    @Override
    protected List<BlockDto> retrieveBlockHashes(Integer blockQuerySize, String blockhash, int blockHeight) throws ServiceException {
        return blockRequestService.getBlockHashesByHeight(blockHeight, blockHeight + blockQuerySize);
    }
}