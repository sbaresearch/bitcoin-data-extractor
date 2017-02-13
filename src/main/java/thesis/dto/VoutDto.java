package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import thesis.model.Address;
import thesis.model.Metainfo;
import thesis.model.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoutDto implements Dto{

    @JsonIgnore
    private NMCTransactionDto transaction;

    private BigDecimal value;

    private int n;

    private ScriptPubKeyDto scriptPubKey;

    private int blockheight;

    public NMCTransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(NMCTransactionDto transaction) {
        this.transaction = transaction;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ScriptPubKeyDto getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(ScriptPubKeyDto scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }
}
