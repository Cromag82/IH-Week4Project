package IHProject.project.Accounts.entities;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.Money.Money;
import lombok.*;
import IHProject.project.AccountHolders.entities.AccountHolders;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

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
    private Money minimumBalance;
    @Embedded
    private Money penaltyFee;
    @Embedded
    private Money monthlyMaintenanceFee;
    private LocalDate creationDate;
    private Status status;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn( name = "primaryOwnerid")
    private AccountHolders checkingPrimaryOwner;

    @Basic(optional=true)
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
