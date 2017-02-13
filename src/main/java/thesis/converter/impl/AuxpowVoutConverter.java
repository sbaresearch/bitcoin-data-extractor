package thesis.converter.impl;

import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.AuxpowVoutDto;
import thesis.exception.ServiceException;
import thesis.model.Address;
import thesis.model.AuxpowAddress;
import thesis.model.AuxpowVout;
import thesis.util.Utils;

import static thesis.util.Utils.nmcToBtcAddress;

@Component
public class AuxpowVoutConverter extends Converter<AuxpowVoutDto, AuxpowVout> {
    @Override
    protected AuxpowVoutDto newDto() {
        return new AuxpowVoutDto();
    }

    @Override
    protected AuxpowVout newEntity() {
        return new AuxpowVout();
    }

    @Override
    public AuxpowVout toEntity(AuxpowVoutDto dto){

        AuxpowVout entity = newEntity();

        this.copyProperties(dto, entity, "addresses");

        if(dto.getAddresses() != null){
            for(String address : dto.getAddresses()){

                entity.addAddress(new AuxpowAddress(address));
                /*try {
                    entity.addAddress(new AuxpowAddress(Utils.nmcToBtcAddress(address)));
                } catch (ServiceException e) {
                    e.printStackTrace();
                }*/
            }
        }

        return entity;
    }
}
