package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thesis.model.Block;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {

    @Query("select b from Block b where b.height = (select max(bb.height) from Block bb)")
    Block findByMaxHeight();

    List<Block> findByHeightGreaterThan(int height);
}
