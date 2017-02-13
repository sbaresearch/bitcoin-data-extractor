package thesis.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import thesis.converter.Converter;
import thesis.dto.NameOpDto;
import thesis.dto.ScriptPubKeyDto;
import thesis.dto.VoutDto;
import thesis.model.Address;
import thesis.model.Vout;

import java.net.IDN;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

@Component
public class VoutConverter extends Converter<VoutDto, Vout> {


    @Override
    protected VoutDto newDto() {
        return new VoutDto();
    }

    @Override
    protected Vout newEntity() {
        return new Vout();
    }

    @Override
    public Vout toEntity(VoutDto dto){


        Vout entity = newEntity();

        this.copyProperties(dto, entity);

        if(dto.getScriptPubKey() != null){
            ScriptPubKeyDto scriptPubKeyDto = dto.getScriptPubKey();
            entity.setAsm(scriptPubKeyDto.getAsm());
            entity.setHex(scriptPubKeyDto.getHex());
            entity.setReqSigs(scriptPubKeyDto.getReqSigs());
            entity.setType(scriptPubKeyDto.getType());


            if(scriptPubKeyDto.getNameOp() != null){

                NameOpDto nameOpDto = scriptPubKeyDto.getNameOp();
                entity.setNameOpName(nameOpDto.getName());
                entity.setNameOpOperation(nameOpDto.getOp());
                entity.setNameOpRand(nameOpDto.getRand());
                entity.setNameOpValue(nameOpDto.getValue());

            }

            for(String address : scriptPubKeyDto.getAddresses()){
                entity.addAddress(new Address(address));
            }
        }

        return entity;
    }
}
