package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.model.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
}
