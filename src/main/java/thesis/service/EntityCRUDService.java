package thesis.service;


import thesis.model.Entity;

/**
 * A Service which provides all CRUD operations for an EntityDto.
 * @param <E>
 * @author Alexei
 */
public interface EntityCRUDService<E extends Entity>
        extends
        EntityCreateService<E>,
        EntityReadService<E>,
        EntityUpdateService<E>,
        EntityDeleteService<E> {
}
