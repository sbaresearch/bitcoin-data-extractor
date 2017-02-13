package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuxpowVinDto implements Dto {

    private String coinbase;

    private String sequence;

    @JsonIgnore
    private AuxpowDto auxpowDto;

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public AuxpowDto getAuxpowDto() {
        return auxpowDto;
    }

    public void setAuxpowDto(AuxpowDto auxpowDto) {
        this.auxpowDto = auxpowDto;
    }
}
