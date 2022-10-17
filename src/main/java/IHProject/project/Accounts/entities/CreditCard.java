package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Money.Money;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Money balance;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn( name = "primaryOwnerid")
    private AccountHolders creditCardPrimaryOwner;

    @Basic(optional=true)
    private AccountHolders secondaryOwner;
    private Money creditLimit;
    private BigDecimal interestRate;
    private Money penaltyFee;
}
