package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thesis.exception.ServiceException;
import thesis.model.Metainfo;
import thesis.repository.MetainfoRepository;
import thesis.service.MetainfoService;
@Service
public class MetainfoServiceImpl implements MetainfoService {

    @Autowired
    private MetainfoRepository repository;

    @Override
    @Transactional
    public Metainfo create(Metainfo toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    @Transactional
    public Metainfo delete(Metainfo toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    @Transactional
    public Metainfo read(Metainfo toRead) throws ServiceException {
        return repository.findOne(toRead.getRunId());
    }

    @Override
    @Transactional
    public Metainfo update(Metainfo toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }

    @Override
    public Metainfo getLastMetainfo() {
        return repository.getLastMetainfo();
    }
}
