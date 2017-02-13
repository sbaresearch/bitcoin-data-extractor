package thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
