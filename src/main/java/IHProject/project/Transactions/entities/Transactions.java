package IHProject.project.Transactions.entities;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Checking checking;

    private final LocalDateTime transactionTime = LocalDateTime.now();

    public Transactions(Checking checking) {
        this.checking = checking;
    }

    public Transactions(long id, Checking checking) {
        this.id = id;
        this.checking = checking;
    }

    //Bonus 1
    public void freezeByAmount() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Transactions> traList = new ArrayList<Transactions>();
        int day = this.transactionTime.getDayOfYear();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Transactions tran : this.checking.getTransactionsList()) {
            if (tran.transactionTime.getDayOfYear() == (day-1)) {
                traList.add(tran);
                sum.add(tran.checking.getBalance());
            }
        }

        Transactions tra2 = this.checking.getTransactionsList().get(this.checking.getTransactionsList().size() - 1);

        if (this.getTransactionTime().getDayOfWeek().getValue() > tra2.getTransactionTime().getDayOfWeek().getValue()
                && this.checking.getBalance().add(tra2.checking.getBalance()).
                compareTo(sum.multiply(BigDecimal.valueOf(1.50))) == 1) {
            this.checking.setStatus(Status.FROZEN);
        }

    }


}
