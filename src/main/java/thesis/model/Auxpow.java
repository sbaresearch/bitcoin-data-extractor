package thesis.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "run_id"})
})
public class Auxpow extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_auxpow_id")
    @SequenceGenerator(initialValue = 1, name = "seq_auxpow_id", sequenceName = "seq_auxpow_id")
    private Integer id;

    private int chainindex;

    @ElementCollection
    @CollectionTable(name="chainmerklebranch", joinColumns=@JoinColumn(name="auxpow_id"))
    private List<String> chainmerklebranch;

    private int index;

    @ElementCollection
    @CollectionTable(name="merklebranch", joinColumns=@JoinColumn(name="auxpow_id"))
    private List<String> merklebranch;

    @Column(columnDefinition="TEXT")
    private String parentblock;

    private String blockhash;

    @Column(columnDefinition="TEXT")
    private String hex;

    private int locktime;

    private int size;

    private String txid;

    private int version;

    @OneToMany(mappedBy = "auxpow", cascade = CascadeType.ALL)
    private List<AuxpowVin> inputs;

    @OneToMany(mappedBy = "auxpow", cascade = CascadeType.ALL)
    private List<AuxpowVout> outputs;

    @OneToOne
    @JoinColumn(name = "block")
    private Block block;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public Auxpow(){
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void addInput(AuxpowVin auxpowVin){
        this.inputs.add(auxpowVin);
        auxpowVin.setAuxpow(this);
    }

    public void addOutput(AuxpowVout auxpowVout){
        this.outputs.add(auxpowVout);
        auxpowVout.setAuxpow(this);
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

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<AuxpowVin> getInputs() {
        return inputs;
    }

    public void setInputs(List<AuxpowVin> inputs) {
        this.inputs = inputs;
    }

    public List<AuxpowVout> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<AuxpowVout> outputs) {
        this.outputs = outputs;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }
}
