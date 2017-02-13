package thesis.service;


import thesis.exception.ServiceException;
import thesis.model.Entity;

/**
 * A service which provides the delete operation for an EntityDto
 * @param <E>
 * @author Alexei
 */
public interface EntityDeleteService<E extends Entity> {

    /**
     * Deletes an existing Entity.
     * @param toDelete containing the ID of the Entity to delete.
     * @return the deleted Entity.
     * @throws ServiceException
     */
    E delete(E toDelete) throws ServiceException;

}
