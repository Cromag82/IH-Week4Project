package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.embeddables.Money;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull
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
    @DecimalMax(value = "100000.00")
    @Digits(integer=6, fraction=2)
    @ColumnDefault(value = "100.00")
    private Money creditLimit;
    @ColumnDefault("0.2")
    private BigDecimal interestRate;
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="cCpFcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="cCpFamount"))
    })
    @Embedded
    private Money penaltyFee;

    public CreditCard(Money balance, @NonNull AccountHolders creditCardPrimaryOwner, Money creditLimit, BigDecimal interestRate, Money penaltyFee) throws Exception {
        this.balance = balance;
        this.creditCardPrimaryOwner = creditCardPrimaryOwner;
        this.creditLimit = creditLimit;
        setCreditCardInterestRate(interestRate);
        this.penaltyFee = penaltyFee;
    }

    public void setCreditCardInterestRate (BigDecimal newInterestRate) throws Exception {
        if (newInterestRate.compareTo(BigDecimal.valueOf(0.1)) == -1) throw new Exception("Non valid minimum interest rate");
        this.interestRate = newInterestRate;
    }
}
