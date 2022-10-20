package IHProject.project.Security.Repository;

import IHProject.project.Security.Role;
import IHProject.project.Security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName (String username);

}
