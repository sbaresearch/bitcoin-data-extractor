package thesis.converter.impl;

import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.VinDto;
import thesis.model.Vin;

@Component
public class VinConverter extends Converter<VinDto, Vin> {
    @Override
    protected VinDto newDto() {
        return new VinDto();
    }

    @Override
    protected Vin newEntity() {
        return new Vin();
    }

    @Override
    public VinDto toDto(Vin entity){

        VinDto dto = newDto();

        this.copyProperties(entity, dto);

        if(entity.getTransaction() != null) dto.setVin_txid(entity.getTransaction().getTxid());

        return dto;
    }

    // TODO: implement missing mappings for scriptSigDto
}


