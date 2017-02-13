package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import thesis.model.Auxpow;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuxpowDto implements Dto {

    private Integer id;

    private int chainindex;

    private List<String> chainmerklebranch;

    private int index;

    private List<String> merklebranch;

    private String parentblock;

    @JsonProperty("tx")
    private AuxPowTxDto tx;

    public AuxpowDto(){
        this.tx = new AuxPowTxDto();
    }
    @JsonIgnore
    private BlockDto block;

    public void addInput(AuxpowVinDto auxpowVinDto){
        this.tx.inputs.add(auxpowVinDto);
        auxpowVinDto.setAuxpowDto(this);
    }

    public void addOutput(AuxpowVoutDto auxpowVoutDto){
        this.tx.outputs.add(auxpowVoutDto);
        auxpowVoutDto.setAuxpowDto(this);
    }

    public int getChainindex() {
        return chainindex;
    }

    public void setChainindex(int chainindex) {
        this.chainindex = chainindex;
    }

    public List<String> getChainmerklebranch() {
        return chainmerklebranch;
    }

    public void setChainmerklebranch(List<String> chainmerklebranch) {
        this.chainmerklebranch = chainmerklebranch;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getMerklebranch() {
        return merklebranch;
    }

    public void setMerklebranch(List<String> merklebranch) {
        this.merklebranch = merklebranch;
    }

    public String getParentblock() {
        return parentblock;
    }

    public void setParentblock(String parentblock) {
        this.parentblock = parentblock;
    }

    public BlockDto getBlock() {
        return block;
    }

    public void setBlock(BlockDto block) {
        this.block = block;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockhash() {
        return tx.blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.tx.blockhash = blockhash;
    }

    public String getHex() {
        return tx.hex;
    }

    public void setHex(String hex) {
        this.tx.hex = hex;
    }

    public int getLocktime() {
        return tx.locktime;
    }

    public void setLocktime(int locktime) {
        this.tx.locktime = locktime;
    }

    public int getSize() {
        return tx.size;
    }

    public void setSize(int size) {
        this.tx.size = size;
    }

    public String getTxid() {
        return tx.txid;
    }

    public void setTxid(String txid) {
        this.tx.txid = txid;
    }

    public int getVersion() {
        return tx.version;
    }

    public void setVersion(int version) {
        this.tx.version = version;
    }

    public List<AuxpowVinDto> getInputs() {
        return tx.inputs;
    }

    public void setInputs(List<AuxpowVinDto> inputs) {
        this.tx.inputs = inputs;
    }

    public List<AuxpowVoutDto> getOutputs() {
        return tx.outputs;
    }

    public void setOutputs(List<AuxpowVoutDto> outputs) {
        this.tx.outputs = outputs;
    }

    public AuxPowTxDto getTx() {
        return tx;
    }

    public void setTx(AuxPowTxDto tx) {
        this.tx = tx;
    }

    public static class AuxPowTxDto {

        private String blockhash;

        private String hex;

        private int locktime;

        private int size;

        private String txid;

        private int version;

        @JsonProperty("vin")
        private List<AuxpowVinDto> inputs;

        @JsonProperty("vout")
        private List<AuxpowVoutDto> outputs;


        public AuxPowTxDto(){
            this.inputs = new ArrayList<>();
            this.outputs = new ArrayList<>();
        }
        public String getBlockhash() {
            return blockhash;
        }

        public void setBlockhash(String blockhash) {
            this.blockhash = blockhash;
        }

        public String getHex() {
            return hex;
        }

        public void setHex(String hex) {
            this.hex = hex;
        }

        public int getLocktime() {
            return locktime;
        }

        public void setLocktime(int locktime) {
            this.locktime = locktime;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<AuxpowVinDto> getInputs() {
            return inputs;
        }

        public void setInputs(List<AuxpowVinDto> inputs) {
            this.inputs = inputs;
        }

        public List<AuxpowVoutDto> getOutputs() {
            return outputs;
        }

        public void setOutputs(List<AuxpowVoutDto> outputs) {
            this.outputs = outputs;
        }
    }
}
