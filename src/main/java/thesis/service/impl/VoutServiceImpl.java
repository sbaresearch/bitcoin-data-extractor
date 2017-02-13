package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import thesis.exception.ServiceException;
import thesis.model.Vout;
import thesis.repository.VoutRepository;
import thesis.service.VoutService;

public class VoutServiceImpl implements VoutService {

    @Autowired
    private VoutRepository repository;

    @Override
    public Vout create(Vout toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    public Vout delete(Vout toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public Vout read(Vout toRead) throws ServiceException {
        return repository.findOne(toRead.getId());
    }

    @Override
    public Vout update(Vout toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }
}
