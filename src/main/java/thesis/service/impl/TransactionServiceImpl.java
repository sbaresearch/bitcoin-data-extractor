package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thesis.exception.ServiceException;
import thesis.model.Transaction;
import thesis.repository.TransactionRepository;
import thesis.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction create(Transaction toCreate) throws ServiceException {

        return repository.save(toCreate);
    }

    @Override
    public Transaction delete(Transaction toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public Transaction read(Transaction toRead) throws ServiceException {
        return repository.findOne(toRead.getTxid());
    }

    @Override
    public Transaction update(Transaction toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }
}
