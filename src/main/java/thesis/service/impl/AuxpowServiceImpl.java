package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import thesis.exception.ServiceException;
import thesis.model.Auxpow;
import thesis.model.Entity;
import thesis.repository.AuxpowRepository;
import thesis.service.AuxpowService;

public class AuxpowServiceImpl implements AuxpowService {

    @Autowired
    private AuxpowRepository repository;

    @Override
    public Auxpow create(Auxpow toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    public Auxpow delete(Auxpow toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public Auxpow read(Auxpow toRead) throws ServiceException {
        return repository.findOne(toRead.getId());
    }

    @Override
    public Auxpow update(Auxpow toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }
}
