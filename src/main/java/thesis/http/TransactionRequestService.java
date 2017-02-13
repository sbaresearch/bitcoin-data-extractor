package thesis.http;

import thesis.exception.ServiceException;
import thesis.model.Transaction;


public interface TransactionRequestService {

    /**
     *
     * @return Transaction which is referenced by the given entity
     */
    Transaction getTransaction(String txid) throws ServiceException;
}
