package IHProject.project.AccountHolders.entities;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.embeddable.Adress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountHolders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate birthDate;

    @Embedded
    private Adress primaryAddress;

    @Embedded
    @Basic(optional=true)
    private Adress mailingAdress;

    @OneToMany
    private List<Checking> accountsList;

    @OneToMany
    private List<CreditCard> creditCardList;

}
