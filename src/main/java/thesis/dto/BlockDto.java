package thesis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockDto implements Dto{

    private String hash;

    private int confirmations;

    private int size;

    private int height;

    private int version;

    private String merkleroot;

    @JsonProperty("n_tx")
    private int numberOfTransactions;

    private Long time;

    private Long nonce;

    private String  bits;

    private BigDecimal difficulty;

    private String chainwork;

    private Long mediantime;

    @JsonProperty("previousblockhash")
    private String previousBlockHash;

    @JsonProperty("nextblockhash")
    private String nextBlockHash;

    private boolean merged_mined;

    @JsonProperty("tx")
    private List<NMCTransactionDto> transactions;

    private AuxpowDto auxpow;

    public BlockDto(String hash) {
        this.hash = hash;
    }

    public BlockDto() {

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMerkleroot() {
        return merkleroot;
    }

    public void setMerkleroot(String merkleroot) {
        this.merkleroot = merkleroot;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public String getChainwork() {
        return chainwork;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public String getNextBlockHash() {
        return nextBlockHash;
    }

    public void setNextBlockHash(String nextBlockHash) {
        this.nextBlockHash = nextBlockHash;
    }

    public List<NMCTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<NMCTransactionDto> transactions) {
        this.transactions = transactions;
    }

    public boolean isMerged_mined() {
        return merged_mined;
    }

    public void setMerged_mined(boolean merged_mined) {
        this.merged_mined = merged_mined;
    }

    public Long getMediantime() {
        return mediantime;
    }

    public void setMediantime(Long mediantime) {
        this.mediantime = mediantime;
    }

    public AuxpowDto getAuxpow() {
        return auxpow;
    }

    public void setAuxpow(AuxpowDto auxpow) {
        this.auxpow = auxpow;
    }
}
