package dao;

import entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction , Long> {
}
