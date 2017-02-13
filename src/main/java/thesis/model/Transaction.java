package thesis.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"txid", "run_id"})
})
public class Transaction extends Entity{

    @Id
    private String txid;

    private long locktime;

    private int version;

    private int confirmations;

    private long time;

    private long blocktime;

    private BigDecimal fees;

    private int blockheight;

    private boolean name_transaction;

    @ManyToOne
    @JoinColumn(name = "blockhash")
    private Block block;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Vin> inputs;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Vout> outputs;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<NMCEntity> entities;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    private String bitcoinBlockHash;

    public Transaction() {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.entities = new ArrayList<>();
    }

    public void addInput(Vin vin){
        this.inputs.add(vin);
        vin.setTransaction(this);
    }

    public void addOutput(Vout vout){
        this.outputs.add(vout);
        vout.setTransaction(this);
    }

    public void addNMCEntity(NMCEntity nmcEntity){
        this.entities.add(nmcEntity);
        nmcEntity.setTransaction(this);
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
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

    public List<Vin> getInputs() {
        return inputs;
    }

    public void setInputs(List<Vin> inputs) {
        this.inputs = inputs;
    }

    public List<Vout> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Vout> outputs) {
        this.outputs = outputs;
    }

    public List<NMCEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<NMCEntity> entities) {
        this.entities = entities;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }

    public boolean isName_transaction() {
        return name_transaction;
    }

    public void setName_transaction(boolean name_transaction) {
        this.name_transaction = name_transaction;
    }

    public String getBitcoinBlockHash() {
        return bitcoinBlockHash;
    }

    public void setBitcoinBlockHash(String bitcoinBlockHash) {
        this.bitcoinBlockHash = bitcoinBlockHash;
    }

}
