package IHProject.project.Accounts.entities;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.embeddable.Money;
import lombok.*;
import IHProject.project.AccountHolders.entities.AccountHolders;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
        this.minimumBalance = minimumBalance;
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
}
