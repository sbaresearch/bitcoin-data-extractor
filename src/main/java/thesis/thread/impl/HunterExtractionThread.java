package thesis.thread.impl;

import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import thesis.converter.impl.BlockConverter;
import thesis.dto.BlockDto;
import thesis.dto.BlockDtoTxFree;
import thesis.dto.NMCTransactionDto;
import thesis.exception.ServiceException;
import thesis.model.Block;
import thesis.model.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("hunter")
@Component
public class HunterExtractionThread extends AbstractExtractionThread {

    @Autowired
    protected BlockConverter converter;

    @Override
    protected void getAndPersistBlocks(List<BlockDto> blockHashes) throws ServiceException {

        logger.debug("Retrieving block information for " + blockHashes.size() + " blockhashes");

        for (BlockDto blockDto : blockHashes) {

            // Get detailed block data

            final Timer.Context blockContext = blockRequestTimer.time();

            BlockDtoTxFree blockDtoTxFree = restBlockRequestService.getBlockByHashNoTxDetails(blockDto.getHash());

            Transaction coinbaseTx = transactionRequestService.getTransaction(blockDtoTxFree.getTxids().get(0));

            BlockDto blockDto1 = new BlockDto(blockDtoTxFree);

            Block block = converter.toEntity(blockDto1);
            block.setTransactions(Arrays.asList(coinbaseTx));

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
}
