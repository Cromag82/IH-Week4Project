package IHProject.project.Accounts.entities;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.embeddables.Money;
import lombok.*;
import IHProject.project.AccountHolders.entities.AccountHolders;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Checking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Money balance;
    private String secreKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="mBcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="mBamount"))
    })
    @DecimalMin(value = "250.00")
    @Digits(integer=3, fraction=2)
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="pFcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="pFamount"))
    })
    private Money penaltyFee;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column=@Column(name="mMFcurrency")),
            @AttributeOverride(name="amount", column = @Column(name="mMFBamount"))
    })
    @ColumnDefault(value = "12.00")
    @Digits(integer=2, fraction=2)
    private Money monthlyMaintenanceFee;
    private LocalDate creationDate;
    @Enumerated
    private Status status;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn( name = "primaryOwnerid")
    private AccountHolders checkingPrimaryOwner;

    @Basic(optional=true)
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private AccountHolders secondaryOwner;

    public Checking(long id, Money balance, String secreKey, Money minimumBalance, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner) {
        this.id = id;
        this.balance = balance;
        this.secreKey = secreKey;
        this.minimumBalance = new Money(BigDecimal.valueOf(250.00));
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Checking(long id, Money balance, String secreKey, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner) {
        this.id = id;
        this.balance = balance;
        this.secreKey = secreKey;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Checking(Money balance, LocalDate creationDate,String secreKey,  @NonNull AccountHolders checkingPrimaryOwner) {
        this.balance = balance;
        this.secreKey = secreKey;
        this.creationDate = creationDate;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
    }

    //normalmente set es void - cambiar si se refiere
    public void setBalance(Money balance) {
        if (((balance.getAmount())).compareTo(this.minimumBalance.getAmount()) == -1) {
            this.balance = new Money (balance.getAmount().subtract(this.penaltyFee.getAmount()));
        } else {
            this.balance = balance;
        }

    }
}
