package thesis.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "run_id"})
})
public class Vin extends thesis.model.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_vin_id")
    @SequenceGenerator(initialValue = 1, name = "seq_vin_id", sequenceName = "seq_vin_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "txid")
    private Transaction transaction;

    private int referenced_vout;

    private String asm;

    @Column(columnDefinition="TEXT")
    private String hex;

    private BigDecimal value;

    private long sequence;

    private String vin_txid;

    private String coinbase;

    private int blockheight;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

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

    public int getReferenced_vout() {
        return referenced_vout;
    }

    public void setReferenced_vout(int referenced_vout) {
        this.referenced_vout = referenced_vout;
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

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
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
