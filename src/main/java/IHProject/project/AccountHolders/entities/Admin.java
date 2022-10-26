package IHProject.project.AccountHolders.entities;

import IHProject.project.Security.entities.Role;
import IHProject.project.Security.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Admin extends User {

    @NotNull
    private String name;


    public Admin(String username, String password, String name) {
        super(username, password);
        this.getRoleList().add(new Role("ADMIN"));
        this.name = name;
    }
}
