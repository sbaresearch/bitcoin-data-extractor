package thesis.http;

import thesis.dto.NMCEntityDto;
import thesis.exception.ServiceException;
import thesis.model.NMCEntity;

import java.util.List;


public interface NMCEntityRequestService {

    /**
     *
     * @return Performs a name scan and returns the requested amount of names
     */
    NMCEntity getName(String name) throws ServiceException;

}
