package IHProject.project.AccountHolders.repositories;

import IHProject.project.AccountHolders.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{


    Optional<Admin> findByName(String name);
}
