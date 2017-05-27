package thesis.http;

import thesis.dto.BlockDto;
import thesis.exception.ServiceException;
import thesis.model.Block;

import java.util.List;

public interface RPCBlockRequestService {

    /**
     * Method: JSON RPC
     * @return Current block height of the  blockchain
     */
    Integer getCurrentBlockHeight() throws ServiceException;

    /**
     * Method: JSON RPC
     * Returns a list of blockhashes between the given start and end blocks
     * @param start - staring height
     * @param end - end height
     * @return - list of blockhashes
     */
    List<BlockDto> getBlockHashesByHeight(int start, int end) throws ServiceException;
}
