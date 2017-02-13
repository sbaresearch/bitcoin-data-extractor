package thesis.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "run_id"})
})
public class AuxpowVout extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_auxpow_vout_id")
    @SequenceGenerator(initialValue = 1, name = "seq_auxpow_vout_id", sequenceName = "seq_auxpow_vout_id")
    private Integer id;

    private int n;

    @Column(columnDefinition="TEXT")
    private String asm;

    @Column(columnDefinition="TEXT")
    private String hex;

    private String type;

    private int reqSigs;

    private BigDecimal value;

    private int blockheight;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "auxpow_vout_addresses",
            joinColumns = @JoinColumn(name = "auxpow_vout_id"),
            inverseJoinColumns = @JoinColumn(name = "address"))
    private List<AuxpowAddress> addresses;

    public AuxpowVout(){
        this.addresses = new ArrayList<>();
    }

    @ManyToOne
    @JoinColumn(name = "auxpow_id")
    private Auxpow auxpow;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<AuxpowAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AuxpowAddress> addresses) {
        this.addresses = addresses;
    }

    public Auxpow getAuxpow() {
        return auxpow;
    }

    public void setAuxpow(Auxpow auxpow) {
        this.auxpow = auxpow;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }

    public void addAddress(AuxpowAddress address) {
        this.addresses.add(address);
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }
}
