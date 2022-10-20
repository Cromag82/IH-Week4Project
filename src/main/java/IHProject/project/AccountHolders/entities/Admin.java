package IHProject.project.AccountHolders.entities;

import IHProject.project.Security.Role;
import IHProject.project.Security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Admin extends User {

    @NotNull
    private String name;

    public Admin(String name) {
        this.getRoleList().add(new Role("ADMIN"));
        this.name = name;
    }
}
