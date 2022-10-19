package IHProject.project.Transactions;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Checking checking;

    private final LocalDateTime transactionTime = LocalDateTime.now();

    public Transactions(long id, Checking checking) {
        this.id = id;
        this.checking = checking;
    }

    //incomplete
    public void freezeByAmount() {
        Transactions tra1 = this;
        Transactions tra2 = this.checking.getTransactionsList().get(this.checking.getTransactionsList().size()-1);
    }


}
