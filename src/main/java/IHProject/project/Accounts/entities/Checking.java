package IHProject.project.Accounts.entities;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.Transactions.entities.Transactions;
import IHProject.project.embeddables.Money;
import lombok.*;
import IHProject.project.AccountHolders.entities.AccountHolders;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    //@DecimalMin(value = "250.00")
    //@Digits(integer=3, fraction=2)
    //@NotNull
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
    //@Digits(integer=2, fraction=2)
    private Money monthlyMaintenanceFee;
    private LocalDate creationDate;
    @Enumerated
    private Status status;

    //  @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "primaryOwnerid")
    private AccountHolders checkingPrimaryOwner;

    @Basic(optional=true)
    @ManyToOne(fetch = FetchType.EAGER/*,cascade = CascadeType.ALL*/)
    private AccountHolders secondaryOwner;

    @OneToMany(mappedBy = "checking", cascade=CascadeType.ALL)
    private List<Transactions> transactionsList;

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public Checking(long id, Money balance, String secreKey, Money minimumBalance, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner) {
        this.id = id;
        this.balance = balance;
        this.secreKey = secreKey;
        setMinimumBalance();
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Checking(long id, Money balance, String secreKey, Money penaltyFee, LocalDate creationDate, Status status, AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner) {
        this.id = id;
        this.balance = balance;
        this.secreKey = secreKey;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Checking(Money balance, LocalDate creationDate,String secreKey, AccountHolders checkingPrimaryOwner) {
        this.balance = balance;
        this.secreKey = secreKey;
        this.creationDate = creationDate;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public void setBalance(Money balance) {
        if (((balance.getAmount())).compareTo(this.minimumBalance.getAmount()) == -1) {
            this.balance = new Money (balance.getAmount().subtract(this.penaltyFee.getAmount()));
        } else {
            this.balance = balance;
        }

    }

    public void setMinimumBalance() {

        this.minimumBalance = new Money(BigDecimal.valueOf(250.00));
    }

    public BigDecimal getBalance() {
        return balance.getAmount();
    }
}
