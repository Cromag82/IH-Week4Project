package IHProject.project.Accounts.entities;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.embeddables.Money;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Savings extends Checking {
    @ColumnDefault(value = "0.00025")
    @DecimalMax(value = "0.5", inclusive = false)
    @Digits(integer=2, fraction=5)
    private BigDecimal interestRate;
    private LocalDate interestApplication = LocalDate.now();

    public Savings(long id, Money balance, String secreKey, @Value("1000") Money minimumBalance, Money penaltyFee, LocalDate creationDate, Status status, @NonNull AccountHolders checkingPrimaryOwner, AccountHolders secondaryOwner, BigDecimal interestRate) throws Exception {
        super(id, balance, secreKey, minimumBalance, penaltyFee, creationDate, status, checkingPrimaryOwner, secondaryOwner);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, LocalDate creationDate, BigDecimal interestRate,@NonNull AccountHolders checkingPrimaryOwner, String secreKey) throws Exception {
        super(balance, creationDate, secreKey, checkingPrimaryOwner);
        setInterestRate(interestRate);
    }

    public void setInterestRate(BigDecimal newInterest) throws Exception {
        if (newInterest.compareTo(BigDecimal.valueOf(0.5)) == 1) throw new Exception("Non valid interest rate");
        this.interestRate = newInterest;
        }

    public void setSavingsMinimumBalance(Money newMinimumBalance) throws Exception {
        if (newMinimumBalance.getAmount().compareTo(BigDecimal.valueOf(100)) == -1) throw new Exception("Non valid minimum balance");
        this.setMinimumBalance(newMinimumBalance);

    }

    //interest applied according years
    public void applyInterest (){
        if ((LocalDate.now().getYear() - this.interestApplication.getYear()) > 1 ) {
            this.setBalance(new Money(
                    this.getBalance().getAmount().multiply(
                    this.interestRate.add(BigDecimal.valueOf(1))).multiply(
                    BigDecimal.valueOf((LocalDate.now().getYear() - this.interestApplication.getYear()))
            )));
        this.interestApplication.plusYears(LocalDate.now().getYear() - this.interestApplication.getYear());
        }

    }
 }


