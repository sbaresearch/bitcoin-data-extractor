package thesis.converter.impl;

import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.AuxpowDto;
import thesis.dto.AuxpowVinDto;
import thesis.model.AuxpowVin;

@Component
public class AuxpowVinConverter extends Converter<AuxpowVinDto, AuxpowVin> {
    @Override
    protected AuxpowVinDto newDto() {
        return new AuxpowVinDto();
    }

    @Override
    protected AuxpowVin newEntity() {
        return new AuxpowVin();
    }
}
