package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thesis.model.Metainfo;

@Repository
public interface MetainfoRepository extends JpaRepository<Metainfo, Integer> {

    @Query("SELECT m FROM Metainfo m WHERE m.runId = (SELECT max(m2.runId) FROM Metainfo m2)")
    Metainfo getLastMetainfo();

}
