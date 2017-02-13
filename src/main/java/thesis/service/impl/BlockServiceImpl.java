package thesis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thesis.exception.ServiceException;
import thesis.model.Block;
import thesis.repository.BlockRepository;
import thesis.service.BlockService;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository repository;

    @Override
    @Transactional
    public Block create(Block toCreate) throws ServiceException {
        return repository.save(toCreate);
    }

    @Override
    @Transactional
    public Block delete(Block toDelete) throws ServiceException {
        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    @Transactional
    public Block read(Block toRead) throws ServiceException {
        return repository.findOne(toRead.getHash());
    }

    @Override
    @Transactional
    public Block update(Block toUpdate) throws ServiceException {
        return repository.save(toUpdate);
    }

    @Override
    @Transactional
    public Block readLastBlock(){
        return repository.findByMaxHeight();
    }

}
