package thesis.service;

import thesis.model.Block;

public interface BlockService extends EntityCRUDService<Block>{

    Block readLastBlock();
}
