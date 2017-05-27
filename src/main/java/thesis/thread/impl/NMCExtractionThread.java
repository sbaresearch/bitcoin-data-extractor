package thesis.thread.impl;

import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.dto.*;
import thesis.exception.NotFoundException;
import thesis.exception.ServiceException;
import thesis.http.NMCEntityRequestService;
import thesis.model.*;

import java.util.ArrayList;
import java.util.List;

@Profile({"nmc", "ncsq"})
@Component
public class NMCExtractionThread extends AbstractExtractionThread {



    private final String NAME_UPDATE_OPERATION_KEYWORD = "name_update";
    private final String NAME_FIRST_UPDATE_OPERATION_KEYWORD = "name_firstupdate";

    @Value("${chain.extractNames}")
    private boolean extractNames;


    @Autowired
    private NMCEntityRequestService nmcEntityRequestService;

    /**
     * Retrieves data on all blocks (incl. transactions and entities) and persists it to the database.
     *
     * @param blockHashes - hashes of the blocks to persist
     * @return - List of persisted blocks
     * @throws ServiceException
     */
    @Override
    protected void getAndPersistBlocks(List<BlockDto> blockHashes) throws ServiceException {

        logger.debug("Retrieving block information for " + blockHashes.size() + " blockhashes");

        for (BlockDto blockDto : blockHashes) {

            // Get detailed block data

            final Timer.Context blockContext = blockRequestTimer.time();

            Block block = restBlockRequestService.getBlockByHash(blockDto.getHash());

            if(extractNames){
                for (Transaction transaction : block.getTransactions()) {
                    transactionCounter++;
                    extractAndsetNMCEntities(transaction);
                }
            }


            blockContext.stop();

            setBlockMetainfo(block);

            blockCounter++;

            /**
             * Persist Data
             */
            final Timer.Context dbContext = dbCallsTimer.time();
            blockService.create(block);
            dbContext.stop();

        }
    }

    /**
     * Retrieves all nmc entities of a transaction
     *
     * @param transaction - transaction to retrieve entities for
     * @return - list of NMCEntities
     * @throws ServiceException
     */
    private void extractAndsetNMCEntities(Transaction transaction) throws ServiceException {

        List<NMCEntity> nmcEntities = new ArrayList<>();
        if (transaction.getOutputs() != null) {

            List<Vout> outputs = transaction.getOutputs();

            for (Vout vout : outputs) {
                if (vout.getNameOpOperation() != null) {

                    // if transaction contains name operations, get the respective nmc entities
                    if (vout.getNameOpOperation().equals(NAME_FIRST_UPDATE_OPERATION_KEYWORD)
                            || vout.getNameOpOperation().equals(NAME_UPDATE_OPERATION_KEYWORD)) {

                        logger.debug(vout.getNameOpName());

                        try {
                            NMCEntity nmcEntity = nmcEntityRequestService.getName(vout.getNameOpName());

                            nmcEntity.setMetainfo(metainfo);
                            nmcEntity.setBlockheight(transaction.getBlock().getHeight());
                            nmcEntity.setExpired_block(currentBlockHeight + nmcEntity.getExpiresIn());
                            transaction.addNMCEntity(nmcEntity);
                        } catch (NotFoundException e) {
                            // if retrieving the name failed, e.g. due to illegal character encoding
                            vout.setNameGetFailed(true);
                        }
                        transaction.setName_transaction(true);
                        nmcEntityCounter++;
                    }
                }
            }
        }
    }

}
