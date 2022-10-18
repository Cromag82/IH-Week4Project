package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.embeddable.Money;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Basic(optional=true)
    private AccountHolders cCsecondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="cLcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="CLamount"))
    })
    private Money creditLimit;
    private BigDecimal interestRate;
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="cCpFcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="cCpFamount"))
    })
    @Embedded
    private Money penaltyFee;
}
