package thesis.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hash", "run_id"})
})
public class Block extends thesis.model.Entity{

    @Id
    private String hash;

    private int confirmations;

    private int size;

    private int height;

    private int version;

    private String merkleroot;

    private int numberOfTransactions;

    private Long time;

    private Long nonce;

    private String bits;

    private BigDecimal difficulty;

    private String chainwork;

    private String previousBlockHash;

    private String nextBlockHash;

    private Long mediantime;

    private boolean merged_mined;

    @Column(nullable = true)
    private String pow_algo;

    @Column(nullable = true)
    private Integer pow_alog_id;

    @Column(nullable = true)
    private String pow_hash;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL)
    private Auxpow auxpow;

    public Block(String hash) {
        this.hash = hash;
    }

    public Block() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        transaction.setBlock(this);
        transaction.setBlockheight(this.getHeight());
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
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

    public Auxpow getAuxpow() {
        return auxpow;
    }

    public void setAuxpow(Auxpow auxpow) {
        this.auxpow = auxpow;
    }

    public String getPow_algo() {
        return pow_algo;
    }

    public void setPow_algo(String pow_algo) {
        this.pow_algo = pow_algo;
    }

    public int getPow_alog_id() {
        return pow_alog_id;
    }

    public void setPow_alog_id(int pow_alog_id) {
        this.pow_alog_id = pow_alog_id;
    }

    public String getPow_hash() {
        return pow_hash;
    }

    public void setPow_hash(String pow_hash) {
        this.pow_hash = pow_hash;
    }
}
