package thesis.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"address", "run_id"})
})
public class Address extends Entity {

    @Id
    private String address;

    @ManyToOne
    @JoinColumn(name = "run_id")
    private Metainfo metainfo;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Metainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(Metainfo metainfo) {
        this.metainfo = metainfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address1 = (Address) o;

        return getAddress() != null ? getAddress().equals(address1.getAddress()) : address1.getAddress() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (getMetainfo() != null ? getMetainfo().hashCode() : 0);
        return result;
    }
}
