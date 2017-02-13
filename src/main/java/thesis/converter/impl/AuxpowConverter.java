package thesis.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.AuxpowDto;
import thesis.dto.AuxpowVinDto;
import thesis.dto.AuxpowVoutDto;
import thesis.dto.NMCTransactionDto;
import thesis.model.Auxpow;
import thesis.model.AuxpowVout;

@Component
public class AuxpowConverter extends Converter<AuxpowDto, Auxpow> {

    @Autowired
    private AuxpowVinConverter auxpowVinConverter;

    @Autowired
    private AuxpowVoutConverter auxpowVoutConverter;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    protected AuxpowDto newDto() {
        return new AuxpowDto();
    }

    @Override
    protected Auxpow newEntity() {
        return new Auxpow();
    }

    public Auxpow toEntity(AuxpowDto dto) {

        Auxpow entity = newEntity();

        this.copyProperties(dto, entity, "inputs", "outputs");

        if (dto.getTx() != null) {
            if (dto.getInputs() != null) {
                for (AuxpowVinDto auxpowVinDto : dto.getInputs()) {
                    entity.addInput(auxpowVinConverter.toEntity(auxpowVinDto));
                }
            }

            if (dto.getOutputs() != null) {
                for (AuxpowVoutDto auxpowVoutDto : dto.getOutputs()) {
                    AuxpowVout auxpowVout = auxpowVoutConverter.toEntity(auxpowVoutDto);
                    entity.addOutput(auxpowVout);
                }
            }

            if (dto.getBlock() != null) {
                entity.setBlockhash(dto.getBlockhash());
            }

            if (dto.getHex() != null) {
                entity.setHex(dto.getHex());
            }

            if(dto.getTxid() != null){
                entity.setTxid(dto.getTxid());
            }

            entity.setLocktime(dto.getLocktime());
            entity.setSize(dto.getSize());
            entity.setVersion(dto.getVersion());
        }


        return entity;
    }
}
