package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import thesis.model.Auxpow;
import thesis.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NMCTransactionDto implements Dto{

    private String txid;

    private long locktime;

    private int version;

    private int confirmations;

    private long time;

    private long blocktime;

    private BigDecimal fees;

    private String blockhash;

    @JsonProperty("vin")
    private List<VinDto> inputs;

    @JsonProperty("vout")
    private List<VoutDto> outputs;

    private AuxpowDto auxpow;

    private String bitcoinBlockHash;

    public NMCTransactionDto(){
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void addInput(VinDto vinDto){
        this.inputs.add(vinDto);
        vinDto.setTransaction(this);
    }

    public void addOutput(VoutDto voutDto){
        this.outputs.add(voutDto);
        voutDto.setTransaction(this);
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public long getLocktime() {
        return locktime;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(long blocktime) {
        this.blocktime = blocktime;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public List<VinDto> getInputs() {
        return inputs;
    }

    public void setInputs(List<VinDto> inputs) {
        this.inputs = inputs;
    }

    public List<VoutDto> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<VoutDto> outputs) {
        this.outputs = outputs;
    }

    public AuxpowDto getAuxpow() {
        return auxpow;
    }

    public void setAuxpow(AuxpowDto auxpow) {
        this.auxpow = auxpow;
    }

    public String getBitcoinBlockHash() {
        return bitcoinBlockHash;
    }

    public void setBitcoinBlockHash(String bitcoinBlockHash) {
        this.bitcoinBlockHash = bitcoinBlockHash;
    }
}
