package thesis.model;

import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "entity")
public class NMCEntity extends Entity{

    @EmbeddedId
    private NMCEntityId id;


    @ManyToOne
    @JoinColumn(name = "txid")
    private Transaction transaction;

    @Column(columnDefinition="TEXT")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    private int blockheight;

    private int expired_block;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public NMCEntity(){
        this.id = new NMCEntityId();
    }

    public String getName() {
        return id.name;
    }

    public void setName(String name) {
        this.id.name = name;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getExpiresIn() {
        return id.expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.id.expiresIn = expiresIn;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }

    public int getExpired_block() {
        return expired_block;
    }

    public void setExpired_block(int expired_block) {
        this.expired_block = expired_block;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }

    @Embeddable
    public static class NMCEntityId implements Serializable{

        private String name;

        private int expiresIn;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }
    }
}
