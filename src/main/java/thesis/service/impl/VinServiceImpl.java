package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import thesis.exception.ServiceException;
import thesis.model.Vin;
import thesis.repository.VinRepository;
import thesis.service.VinService;

public class VinServiceImpl implements VinService {

    @Autowired
    private VinRepository repository;

    @Override
    public Vin create(Vin toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    public Vin delete(Vin toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public Vin read(Vin toRead) throws ServiceException {
        return repository.findOne(toRead.getId());
    }

    @Override
    public Vin update(Vin toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }
}
