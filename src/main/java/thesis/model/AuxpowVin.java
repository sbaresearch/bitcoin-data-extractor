package thesis.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "run_id"})
})
public class AuxpowVin extends Entity{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_auxpow_vin_id")
    @SequenceGenerator(initialValue = 1, name = "seq_auxpow_vin_id", sequenceName = "seq_auxpow_vin_id")
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String coinbase;

    private String sequence;

    @ManyToOne
    @JoinColumn(name = "auxpow_id")
    private Auxpow auxpow;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
