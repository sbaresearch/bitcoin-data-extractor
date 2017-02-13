package thesis.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import thesis.dto.Dto;
import thesis.dto.DtoList;
import thesis.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A converter for DTOs and Entities. Provides default behavior.
 *
 * @param <D> Type of DTOs
 * @param <E> Type of Entities
 */
public abstract class Converter<D extends Dto, E extends Entity> {

    /**
     * Converts the Entity to a DTO and copies its properties.
     * @param entity
     * @return
     */
    public D toDto(E entity) {
        D dto = newDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    /**
     * Converts the DTO to an Entity and copies its properties.
     * @param dto
     * @return
     */
    public E toEntity(D dto) {
        E entity = newEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    /**
     * Converts the DTO to an Entity and copies its properties from DTO and an existing Entity
     * (used to fetch related Entities from the existing Entity).
     * @param dto
     * @param oldEntity The existing Entity.
     * @return
     */
    public E toEntity(D dto, E oldEntity) {
        return toEntity(dto);
    }

    protected void copyProperties(Object from, Object to) {
        BeanUtils.copyProperties(from, to);
    }

    protected  void copyProperties(Object from, Object to, String... ignoreProperties){
        BeanUtils.copyProperties(from, to, ignoreProperties);
    }

    /**
     * Creates an instance of the DTO.
     * @return
     */
    protected abstract D newDto();

    /**
     * Creates an instance of the Entity.
     * @return
     */
    protected abstract E newEntity();


    /**
     * Converts a list of Entities to a DtoList and copies the objects' values.
     * @param entities
     * @return
     */
    public DtoList<D> toDtoList(List<E> entities) {
        ArrayList<D> result = new ArrayList<>();
        for (E entity: entities) {
            result.add(toDto(entity));
        }
        return new DtoList<>(result);
    }
}