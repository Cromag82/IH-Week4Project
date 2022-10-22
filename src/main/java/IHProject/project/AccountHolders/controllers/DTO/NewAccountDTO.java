package IHProject.project.AccountHolders.controllers.DTO;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.embeddables.Money;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class NewAccountDTO {

    private Money balance;
    private String secreKey;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

    private AccountHolders primaryOwner;

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

    public AccountHolders getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public NewAccountDTO(Money balance, String secreKey, LocalDate creationDate, AccountHolders primaryOwner) {
        this.balance = balance;
        this.secreKey = secreKey;
        this.creationDate = creationDate;
        this.primaryOwner = primaryOwner;
    }
}
