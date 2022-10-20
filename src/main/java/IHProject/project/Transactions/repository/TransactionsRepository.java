package IHProject.project.Transactions.repository;

import IHProject.project.Transactions.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
