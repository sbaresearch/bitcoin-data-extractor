package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.model.Vin;

@Repository
public interface VinRepository extends JpaRepository<Vin, Integer> {
}
