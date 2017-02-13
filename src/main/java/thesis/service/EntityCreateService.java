package thesis.service;

import thesis.exception.ServiceException;
import thesis.model.Entity;

/**
 * A service which provides the create operation for an EntityDto
 * @param <E>
 * @author Alexei
 *
 */
public interface EntityCreateService<E extends Entity> {

    /**
     * Creates and persists a Entity.
     * @param toCreate A valid Entity.
     * @return the created Entity with its assigned ID.
     * @throws ServiceException
     */
    E create(E toCreate) throws ServiceException;

}
