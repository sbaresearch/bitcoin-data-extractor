package thesis.thread.impl;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import thesis.dto.BlockDto;
import thesis.exception.ServiceException;
import thesis.http.BlockRequestService;
import thesis.model.*;
import thesis.service.BlockService;
import thesis.service.MetainfoService;
import thesis.service.SendMailService;
import thesis.thread.ExtractionService;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractExtractionThread implements ExtractionService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BlockRequestService blockRequestService;

    @Autowired
    protected BlockService blockService;

    @Autowired
    protected MetainfoService metainfoService;

    @Autowired
    protected SendMailService sendMailService;

    @Value("${chain.firstBlockHash}")
    protected String firstBlockHash;

    @Value("${chain.firstBlockHeight}")
    protected int firstBlockHeight;

    @Value("${chain.genesisBlock}")
    protected String genesisBlock;

    @Value("${chain.blockQuerySize}")
    protected Integer blockQuerySize;

    @Value("${email.sendMail}")
    protected boolean sendMail;

    @Value("${chain.testRun}")
    protected boolean testRun;

    @Value("${chain.newRun}")
    protected boolean newRun;

    @Value("${chain.runId}")
    protected int runId;

    protected final int BLOCK_SAFETY_BUFFER = 24;

    protected Timestamp startTime;
    protected Timestamp endTime;
    protected int nmcEntityCounter;
    protected int transactionCounter;
    protected int blockCounter;
    protected int currentBlockHeight;
    protected String operationMessage;
    protected Metainfo metainfo;


    protected static final MetricRegistry metricRegistry = new MetricRegistry();
    protected Timer blockRequestTimer = metricRegistry.timer("block-requests:");
    protected Timer hashRequestTimer = metricRegistry.timer("hash-requests:");
    protected Timer dbCallsTimer = metricRegistry.timer("database-queries");

    public AbstractExtractionThread() {
        startTime = null;
        endTime = null;
        nmcEntityCounter = 0;
        transactionCounter = 0;
        blockCounter = 0;
        currentBlockHeight = 0;
        operationMessage = "";
        metainfo = new Metainfo();
    }

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
            logger.info("Safety Buffer: " + BLOCK_SAFETY_BUFFER + " blocks. Actual blockheight is " + (currentBlockHeight + BLOCK_SAFETY_BUFFER));
            logger.info("Retrieving blockhashes");

            int blockHeight = firstBlockHeight;
            boolean finished = false;
            String blockhash = firstBlockHash;

            Block lastBlock = blockService.readLastBlock();
            if (lastBlock == null) {
                if (!newRun) throw new ServiceException("No previous data found. Please configure to start a new run");
                blockhash = genesisBlock;
                blockHeight = 0;
            } else {
                blockHeight = lastBlock.getHeight();
                blockhash = lastBlock.getHash();
            }

            while (!finished) {

                // check if end of chain has been reached
                if (currentBlockHeight - blockHeight < blockQuerySize) {
                    blockQuerySize = currentBlockHeight - blockHeight;
                    finished = true;
                }

                // start http request performance meter
                final Timer.Context hashcontext = hashRequestTimer.time();

                List<BlockDto> blockhashes = blockRequestService.getBlockHashes(blockQuerySize, blockhash);

                // stop http request performance meter
                hashcontext.stop();

                /**
                 * Retrieve and persist blocks, given the current list of blockhashes
                 */
                getAndPersistBlocks(blockhashes);

                blockhash = blockhashes.get(blockhashes.size() - 1).getHash();
                blockHeight += blockQuerySize;

                int latest = blockhashes.get(blockhashes.size() - 1).getHeight();
                double percent = (double) Math.round((latest / (double) currentBlockHeight * 100) * 100) / 100;
                logger.info("Block " + latest + " - " + percent + "%");

            }
            logger.info("Finished operation... terminating");

        } catch (Exception e) {
            logger.error(e.getMessage());

            operationMessage = "Operation failed! \n"
                    + "Error message : " + e.getMessage();

        } finally {
            /**
             * Update metainfo and send email report
             */
            finalizeAndReport();
        }

    }


    /**
     * Performs initialization operations:
     * 1) initializes helper variables
     * 2) creates or retrieves Metainfo
     * 3) Retrieves and/or sets the current block height
     */
    protected void init() throws ServiceException {


        startTime = new Timestamp(System.currentTimeMillis());

        /**
         * Create meta information
         */
        metainfo = createMetainfo();

        logger.info("Retrieving current blockheight");

        if (testRun) {
            currentBlockHeight = 15;
            blockQuerySize = 10;

            logger.info("######### TEST RUN #########");
        } else {
            /**
             * Create metainformation to obtain run_id for auditing
             */
            if (newRun) {
                metainfo = createMetainfo();
            } else if (runId != -1) {
                metainfo = getMetainfoById(runId);
            } else {
                metainfo = retrieveLastMetainfo();
            }
            logger.info(metainfo.toString());
            logger.info("Retrieving current blockheight");

            currentBlockHeight = blockRequestService.getCurrentBlockHeight();

            currentBlockHeight = currentBlockHeight - BLOCK_SAFETY_BUFFER;
        }

    }

    /**
     * Retrieves data on all blocks (incl. transactions and entities) and persists it to the database.
     *
     * @param blockHashes - hashes of the blocks to persist
     * @return - List of persisted blocks
     * @throws ServiceException
     */
    protected void getAndPersistBlocks(List<BlockDto> blockHashes) throws ServiceException {

        logger.debug("Retrieving block information for " + blockHashes.size() + " blockhashes");

        for (BlockDto blockDto : blockHashes) {

            logger.debug("Block " + blockDto.getHash());

            // start block http request performance meter
            final Timer.Context blockContext = blockRequestTimer.time();

            // get detailed block data incl. block transactions
            Block block = blockRequestService.getBlockByHash(blockDto.getHash());

            // stop block http request performance meter
            blockContext.stop();

            // set metainfo for the given block and its object tree
            setBlockMetainfo(block);

            // update counters
            transactionCounter += block.getTransactions().size();
            blockCounter++;

            /**
             * Persist Data
             */

            // start database performance meter
            final Timer.Context dbContext = dbCallsTimer.time();

            blockService.create(block);

            // stop database performance meter
            dbContext.stop();

        }
    }


    /**
     * Creates a meta information entry in the database
     *
     * @return - created meta information object
     * @throws ServiceException
     */
    protected Metainfo createMetainfo() throws ServiceException {
        metainfo.setStartTime(startTime);
        metainfo.setStartBlock(firstBlockHeight);
        return metainfoService.create(metainfo);
    }

    /**
     * Retrieves the last created Metainfo object
     *
     * @return - last created Metainfo
     */
    protected Metainfo retrieveLastMetainfo() {
        return metainfoService.getLastMetainfo();
    }


    /**
     * Retrieves the Metainfo given a runId
     *
     * @param runId - id of the requested Metainfo
     * @return - Metainfo object representing the run defined by runId
     * @throws ServiceException
     */
    protected Metainfo getMetainfoById(int runId) throws ServiceException {

        return metainfoService.read(new Metainfo(runId));
    }

    /**
     * Sets the metainfo for the given block
     * and the complete related object tree:
     * Transaction, Vin, Vout.
     *
     * @param block
     */
    protected void setBlockMetainfo(Block block) {
        block.setMetainfo(metainfo);
        if (block.getTransactions() != null) {
            for (Transaction transaction : block.getTransactions()) {
                transaction.setMetainfo(metainfo);
                for (Vout vout : transaction.getOutputs()) vout.setMetainfo(metainfo);
                for (Vin vin : transaction.getInputs()) vin.setMetainfo(metainfo);
            }
        }
    }

    /**
     * Updates and persists the current Metainfo object
     *
     * @throws ServiceException - persisting failed
     */
    protected void updateMetainfo() throws ServiceException {
        endTime = new Timestamp(System.currentTimeMillis());
        // set metainfo and send mail
        metainfo.setEndTime(endTime);
        metainfo.setExtracted_records(nmcEntityCounter);
        metainfo.setExtracted_transactions(transactionCounter);
        metainfo.setExtracted_blocks(blockCounter);
        metainfo.setEndBlock(currentBlockHeight);
        metainfo.setNotes(operationMessage);

        metainfoService.update(metainfo);
    }


    /**
     * Starts performance reporting.
     * Output goes to console.
     */
    protected static void startPerformanceReporting() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.MINUTES);
    }

    /**
     * Sends email report:
     * [Success] or [Error + Cause]
     * + string representation of the current Metainfo object
     */
    protected void sendMailReport() {
        String mailMessage = operationMessage + metainfo.toString();
        try {
            sendMailService.sendMail(mailMessage);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Finalizes workflow:
     * 1) Updates metainfo
     * 2) If option is turned on, sends email report
     */
    protected void finalizeAndReport() {
        try {
            updateMetainfo();
        } catch (ServiceException e) {
            operationMessage = "Operation failed! \n"
                    + "Error message : " + e.getMessage() + "\n";

            logger.error(e.getMessage());

        } finally {
            if (sendMail) {
                sendMailReport();
            }
        }
    }

}

