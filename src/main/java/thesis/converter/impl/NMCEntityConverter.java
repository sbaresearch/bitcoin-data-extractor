package thesis.converter.impl;

import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.NMCEntityDto;
import thesis.model.Address;
import thesis.model.NMCEntity;

@Component
public class NMCEntityConverter extends Converter<NMCEntityDto, NMCEntity> {
    @Override
    protected NMCEntityDto newDto() {
        return new NMCEntityDto();
    }

    @Override
    protected NMCEntity newEntity() {
        return new NMCEntity();
    }

    @Override
    public NMCEntityDto toDto(NMCEntity entity){

        NMCEntityDto dto = newDto();

        this.copyProperties(entity, dto);

        if(entity.getTransaction() != null) dto.setTxid(entity.getTransaction().getTxid());
        if(entity.getAddress() != null) dto.setAddress(entity.getAddress().getAddress());
        return dto;
    }

    @Override
    public NMCEntity toEntity(NMCEntityDto dto){

        NMCEntity entity = newEntity();

        this.copyProperties(dto, entity);

        if(dto.getAddress() != null) entity.setAddress(new Address(dto.getAddress()));

        return entity;
    }

}
