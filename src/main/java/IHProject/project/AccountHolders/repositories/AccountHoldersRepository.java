package IHProject.project.AccountHolders.repositories;

import IHProject.project.AccountHolders.entities.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Long> {
}
