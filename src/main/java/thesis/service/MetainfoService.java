package thesis.service;

import thesis.dto.MetainfoDto;
import thesis.model.Metainfo;

public interface MetainfoService extends EntityCRUDService<Metainfo> {
    Metainfo getLastMetainfo();
}
