package IHProject.project.AccountHolders.controllers.DTO;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.embeddables.Money;

import java.math.BigDecimal;

public class CreditCardDTO {

    private Money balance;
    private AccountHolders creditCardPrimaryOwner;
    private Money creditLimit;
    private BigDecimal interestRate;
    private Money penaltyFee;

    public CreditCardDTO(Money balance, AccountHolders creditCardPrimaryOwner, Money creditLimit, BigDecimal interestRate, Money penaltyFee) {
        this.balance = balance;
        this.creditCardPrimaryOwner = creditCardPrimaryOwner;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolders getCreditCardPrimaryOwner() {
        return creditCardPrimaryOwner;
    }

    public void setCreditCardPrimaryOwner(AccountHolders creditCardPrimaryOwner) {
        this.creditCardPrimaryOwner = creditCardPrimaryOwner;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}
