package thesis.service;


import thesis.exception.ServiceException;
import thesis.model.Entity;

public interface EntityReadService<E extends Entity> {

    /**
     * Returns a specific Entity.
     * @param toRead containing the ID of the requested Entity.
     * @return
     * @throws ServiceException
     */
    E read(E toRead) throws ServiceException;

}
