package thesis.service;

import thesis.model.Block;

import java.util.List;

public interface BlockService extends EntityCRUDService<Block>{

    Block readLastBlock();

    List<Block> readBlocksSinceHeight(int height);
}
