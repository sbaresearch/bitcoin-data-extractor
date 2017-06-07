package thesis.http;

import thesis.dto.BlockDto;
import thesis.dto.BlockDtoTxFree;
import thesis.exception.ServiceException;
import thesis.model.Block;

import java.util.List;


public interface RESTBlockRequestService {

    /**
     * Method: REST
     * @return Current block height of the  blockchain
     */
    Integer getCurrentBlockHeight() throws ServiceException;

    /**
     * Method: REST
     * @return Block in which the given transaction is stored
     */
    Block getBlockByHash(String hash) throws ServiceException;

    BlockDtoTxFree getBlockByHashNoTxDetails(String hash) throws ServiceException;

    /**
     * Method: REST
     * Retrieves a list of "count" blockhashes given the starting hash
     * @param count - amount of block hashes to be returned
     * @param hash - starting hash
     * @return list of "count" block hashes starting from the given hash
     * @throws ServiceException
     */
    List<BlockDto> getBlockHashes(Integer count, String hash) throws ServiceException;

    List<BlockDto> getBlockHashesHex(Integer count, String hash) throws ServiceException;
}
