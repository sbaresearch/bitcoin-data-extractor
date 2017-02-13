package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.model.NMCEntity;

@Repository
public interface NMCEntityRepository extends JpaRepository<NMCEntity, String> {
}
