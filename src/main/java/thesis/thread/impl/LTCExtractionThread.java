package thesis.thread.impl;

import com.codahale.metrics.Timer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;

@Profile({"ltc", "emer"})
@Component
public class LTCExtractionThread extends AbstractExtractionThread {

    @Override
    /**
     * The method:
     * 1) gets block hashes starting from first block hash
     * 2) gets transactions for block
     * 3) gets nmc entities for transactions containing name operations
     * 4) persists the data including meta information
     */
    public void run() {

        try {

            // initialization
            init();

            // Start performance reporting
            startPerformanceReporting();

            logger.info("Analysis starts at " + firstBlockHeight + " and will stop at block " + currentBlockHeight);
            logger.info("Retrieving blockhashes");

            int blockHeight = firstBlockHeight;
            boolean finished = false;


            while (!finished) {

                // check if end of chain has been reached
                if (currentBlockHeight - blockHeight < blockQuerySize) {
                    blockQuerySize = currentBlockHeight - blockHeight;
                    finished = true;
                }

                // start http request performance meter
                final Timer.Context hashcontext = hashRequestTimer.time();

                List<BlockDto> blockhashes = blockRequestService.getBlockHashesByHeight(blockHeight, blockHeight + blockQuerySize);

                // stop http request performance meter
                hashcontext.stop();

                /**
                 * Retrieve and persist blocks, given the current list of blockhashes
                 */
                getAndPersistBlocks(blockhashes);

                blockHeight += blockQuerySize;

                logger.info("Block " + blockHeight + " - Progress: " + Math.round((blockHeight / currentBlockHeight) * 100) + "%");
            }

            logger.info("Finished operation... terminating");

        } catch (Exception e) {
            logger.error(e.getMessage());

            operationMessage = "Operation failed! \n"
                    + "Error message : " + e.getMessage();

        } finally {
             finalizeAndReport();
        }

    }
}