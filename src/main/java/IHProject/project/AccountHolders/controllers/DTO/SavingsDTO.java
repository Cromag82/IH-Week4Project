package IHProject.project.AccountHolders.controllers.DTO;
import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.embeddables.Money;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingsDTO {

    Money balance;
    String secreKey;
    LocalDate creationDate;
    AccountHolders checkingPrimaryOwner;

    AccountHolders checkingSecOwner;
    BigDecimal interestRate;

    public SavingsDTO(Money balance, String secreKey, LocalDate creationDate, AccountHolders checkingPrimaryOwner, AccountHolders checkingSecOwner,  BigDecimal interestRate) {

        this.balance = balance;
        this.secreKey = secreKey;
        this.creationDate = creationDate;
        this.checkingPrimaryOwner = checkingPrimaryOwner;
        this.checkingSecOwner = checkingSecOwner;
        this.interestRate = interestRate;
    }

    public AccountHolders getCheckingSecOwner() {
        return checkingSecOwner;
    }

    public void setCheckingSecOwner(AccountHolders checkingSecOwner) {
        this.checkingSecOwner = checkingSecOwner;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getSecreKey() {
        return secreKey;
    }

    public void setSecreKey(String secreKey) {
        this.secreKey = secreKey;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public AccountHolders getCheckingPrimaryOwner() {
        return checkingPrimaryOwner;
    }

    public void setCheckingPrimaryOwner(AccountHolders checkingPrimaryOwner) {
        this.checkingPrimaryOwner = checkingPrimaryOwner;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
