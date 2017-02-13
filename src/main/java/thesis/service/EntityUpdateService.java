package thesis.service;


import thesis.exception.ServiceException;
import thesis.model.Entity;

/**
 * A service which provides the update operation for an EntityDto
 *
 * @param <E>
 * @author Alexei
 */
public interface EntityUpdateService<E extends Entity> {

    /**
     * Updates an existing Entity.
     *
     * @param toUpdate A valid Entity.
     * @return the updated Entity.
     * @throws ServiceException
     */
    E update(E toUpdate) throws ServiceException;

}
