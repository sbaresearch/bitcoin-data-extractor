package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.model.Vout;

@Repository
public interface VoutRepository extends JpaRepository<Vout, Integer> {
}
