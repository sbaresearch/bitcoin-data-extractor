package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import thesis.model.Transaction;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VinDto implements Dto {

    @JsonIgnore
    private NMCTransactionDto transaction;

    @JsonProperty("vout")
    private int referenced_vout;

    private ScriptSigDto scriptSig;

    private BigDecimal value;

    private long sequence;

    @JsonProperty("txid")
    String vin_txid;

    private String coinbase;

    private int blockheight;


    public NMCTransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(NMCTransactionDto transaction) {
        this.transaction = transaction;
    }

    public int getReferenced_vout() {
        return referenced_vout;
    }

    public void setReferenced_vout(int referenced_vout) {
        this.referenced_vout = referenced_vout;
    }

    public ScriptSigDto getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(ScriptSigDto scriptSig) {
        this.scriptSig = scriptSig;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getVin_txid() {
        return vin_txid;
    }

    public void setVin_txid(String vin_txid) {
        this.vin_txid = vin_txid;
    }

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }
}
