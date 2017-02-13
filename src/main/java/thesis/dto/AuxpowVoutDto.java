package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import thesis.model.Address;
import thesis.model.Auxpow;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuxpowVoutDto implements Dto {

    private int n;

    private String asm;

    private String hex;

    private String type;

    private int reqSigs;

    private BigDecimal value;

    private List<String> addresses;

    private int blockheight;

    @JsonIgnore
    private AuxpowDto auxpowDto;

    @JsonProperty("scriptPubKey")
    public void setScriptPubKey(Map<String, Object> scriptPubKey){
        if(scriptPubKey.get("addresses") != null) addresses = (List<String>) scriptPubKey.get("addresses");
        if( scriptPubKey.get("asm") != null) asm = (String) scriptPubKey.get("asm");
        if(scriptPubKey.get("hex") != null) hex = (String) scriptPubKey.get("hex");
        if(scriptPubKey.get("reqSigs") != null) reqSigs = (Integer) scriptPubKey.get("reqSigs");
        if( scriptPubKey.get("type") != null) type = (String) scriptPubKey.get("type");
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReqSigs() {
        return reqSigs;
    }

    public void setReqSigs(int reqSigs) {
        this.reqSigs = reqSigs;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public AuxpowDto getAuxpowDto() {
        return auxpowDto;
    }

    public void setAuxpowDto(AuxpowDto auxpowDto) {
        this.auxpowDto = auxpowDto;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }
}
