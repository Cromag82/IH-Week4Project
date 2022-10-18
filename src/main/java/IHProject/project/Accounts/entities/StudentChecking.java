package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.embeddables.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class StudentChecking extends Checking{

    public StudentChecking(long id, Money balance, String secreKey, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner) {
        super(id, balance, secreKey, penaltyFee, creationDate, status, checkingPrimaryOwner, secondaryOwner);
    }

    public StudentChecking(Money balance, LocalDate creationDate, String secreKey, @NonNull AccountHolders checkingPrimaryOwner) {
        super(balance, creationDate, secreKey, checkingPrimaryOwner);
    }
}
