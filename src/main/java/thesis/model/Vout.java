package thesis.model;

import com.fasterxml.jackson.annotation.JsonRawValue;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "run_id"})
})
public class Vout extends thesis.model.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_vout_id")
    @SequenceGenerator(initialValue = 1, name = "seq_vout_id", sequenceName = "seq_vout_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "txid")
    private Transaction transaction;

    private BigDecimal value;

    private int n;

    private String nameOpName;

    private String nameOpOperation;

    @Column(columnDefinition="TEXT")
    private String nameOpValue;

    @Column(columnDefinition="TEXT")
    private String nameOpRand;

    @Column(columnDefinition="TEXT")
    private String asm;

    @Column(columnDefinition="TEXT")
    private String hex;

    private String type;

    private int reqSigs;

    private Boolean nameGetFailed;

    private int blockheight;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "vout_addresses",
            joinColumns = @JoinColumn(name = "vout_id"),
            inverseJoinColumns = @JoinColumn(name = "address"))
    private List<Address> addresses;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public Vout(){
        this.addresses = new ArrayList<>();
    }

    public void addAddress(Address address){
        this.addresses.add(address);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
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

    public String getNameOpName() {
        return nameOpName;
    }

    public void setNameOpName(String nameOpName) {
        this.nameOpName = nameOpName;
    }

    public String getNameOpOperation() {
        return nameOpOperation;
    }

    public void setNameOpOperation(String nameOpOperation) {
        this.nameOpOperation = nameOpOperation;
    }

    public String getNameOpValue() {
        return nameOpValue;
    }

    public void setNameOpValue(String nameOpValue) {
        this.nameOpValue = nameOpValue;
    }

    public String getNameOpRand() {
        return nameOpRand;
    }

    public void setNameOpRand(String nameOpRand) {
        this.nameOpRand = nameOpRand;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReqSigs() {
        return reqSigs;
    }

    public void setReqSigs(int reqSigs) {
        this.reqSigs = reqSigs;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }

    public Boolean getNameGetFailed() {
        return nameGetFailed;
    }

    public void setNameGetFailed(Boolean nameGetFailed) {
        this.nameGetFailed = nameGetFailed;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }
}
