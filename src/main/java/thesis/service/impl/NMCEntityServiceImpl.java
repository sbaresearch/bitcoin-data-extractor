package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thesis.exception.ServiceException;
import thesis.model.NMCEntity;
import thesis.repository.NMCEntityRepository;
import thesis.service.NMCEntityService;
@Service
public class NMCEntityServiceImpl implements NMCEntityService {

    @Autowired
    private NMCEntityRepository repository;

    @Override
    public NMCEntity create(NMCEntity toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    public NMCEntity delete(NMCEntity toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    public NMCEntity read(NMCEntity toRead) throws ServiceException {
        return repository.findOne(toRead.getName());
    }

    @Override
    public NMCEntity update(NMCEntity toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }
}
