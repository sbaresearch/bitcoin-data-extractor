package thesis.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.NMCTransactionDto;
import thesis.dto.VinDto;
import thesis.dto.VoutDto;
import thesis.exception.ServiceException;
import thesis.model.Block;
import thesis.model.Transaction;
import thesis.model.Vin;
import thesis.model.Vout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter extends Converter<NMCTransactionDto, Transaction> {

    @Autowired
    private VoutConverter voutConverter;

    @Autowired
    private VinConverter vinConverter;

    @Override
    protected NMCTransactionDto newDto() {
        return new NMCTransactionDto();
    }

    @Override
    protected Transaction newEntity() {
        return new Transaction();
    }

    @Override
    public NMCTransactionDto toDto(Transaction entity){

        NMCTransactionDto dto = newDto();

        this.copyProperties(entity, dto);

        if(entity.getBlock() != null) dto.setBlockhash(entity.getBlock().getHash());


        if(entity.getOutputs() != null){
            for(Vout vout : entity.getOutputs()){
                dto.addOutput(voutConverter.toDto(vout));
            }
        }

        if(entity.getInputs() != null){
            for(Vin vin : entity.getInputs()){
                dto.addInput(vinConverter.toDto(vin));
            }
        }

        return dto;
    }

    @Override
    public Transaction toEntity(NMCTransactionDto dto) {

        Transaction entity = newEntity();

        this.copyProperties(dto, entity, "inputs", "outputs");

        if(dto.getBlockhash() != null) entity.setBlock(new Block(dto.getBlockhash()));

        if(dto.getOutputs() != null){
            for(VoutDto voutDto : dto.getOutputs()){
                Vout vout = voutConverter.toEntity(voutDto);
                vout.setBlockheight(entity.getBlockheight());
                entity.addOutput(vout);
            }
        }

        if(dto.getInputs() != null){
            for(VinDto vinDto : dto.getInputs()){
                Vin vin = vinConverter.toEntity(vinDto);
                vin.setBlockheight(entity.getBlockheight());
                entity.addInput(vin);
            }
        }

        return entity;
    }
}
