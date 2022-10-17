package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.Money.Money;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Savings extends Checking {

   private double interestRate;

    public Savings(long id, Money balance, String secreKey, Money minimumBalance, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner, double interestRate) {
        super(id, balance, secreKey, minimumBalance, penaltyFee, creationDate, status, checkingPrimaryOwner, secondaryOwner);
        this.interestRate = interestRate;
    }
}

