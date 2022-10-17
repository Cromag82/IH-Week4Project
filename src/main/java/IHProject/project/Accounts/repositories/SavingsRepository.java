package IHProject.project.Accounts.repositories;

import IHProject.project.Accounts.entities.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {
}

