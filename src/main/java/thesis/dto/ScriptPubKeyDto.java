package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScriptPubKeyDto implements Dto {

    private String asm;

    private String hex;

    private int reqSigs;

    private String type;

    @JsonIgnore
    private NameOpDto nameOp;

    private List<String> addresses;


    public ScriptPubKeyDto() {
        this.addresses = new ArrayList<>();
    }

    public void addAddress(String address){
        this.addresses.add(address);
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

    public int getReqSigs() {
        return reqSigs;
    }

    public void setReqSigs(int reqSigs) {
        this.reqSigs = reqSigs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public NameOpDto getNameOp() {
        return nameOp;
    }

    @JsonIgnore
    public void setNameOp(NameOpDto nameOp) {
        this.nameOp = nameOp;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
