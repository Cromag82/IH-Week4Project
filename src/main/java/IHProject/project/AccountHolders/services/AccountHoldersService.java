package IHProject.project.AccountHolders.services;

import IHProject.project.AccountHolders.controllers.DTO.TransferMoneyDTO;
import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.AccountHolders.repositories.AccountHoldersRepository;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHoldersService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    public BigDecimal getBalance(long id) {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        return checkingRepository.findById(id).get().getBalance();
    }

    public BigDecimal getCCBalance(long id) {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        return creditCardRepository.findById(id).get().getBalance();
    }

    //el name no tiene funcionalidad en el método, dejar para seguridad ??
    public BigDecimal transferFunds(TransferMoneyDTO transferMoneyDTO) throws Exception, ResponseStatusException{
        checkingRepository.findById(transferMoneyDTO.getIdOrigin()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Holder Account not found"));
        checkingRepository.findById(transferMoneyDTO.getIdDestiny()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Destination Account not found"));
        Checking holder = checkingRepository.findById(transferMoneyDTO.getIdOrigin()).get();
        Checking dest = checkingRepository.findById(transferMoneyDTO.getIdDestiny()).get();
        if (holder.getBalance().compareTo(new BigDecimal(transferMoneyDTO.getAmount())) == 1) {
            holder.setBalance(new Money(holder.getBalance().subtract(new BigDecimal(transferMoneyDTO.getAmount()))));
            dest.setBalance(new Money(dest.getBalance().add(new BigDecimal(transferMoneyDTO.getAmount()))));
            checkingRepository.save(holder);
            checkingRepository.save(dest);
            return checkingRepository.findById(transferMoneyDTO.getIdDestiny()).get().getBalance();
        } else throw new Exception("Not enough funds");
    }

    public AccountHolders newAHAccount(AccountHolders aH) {
        return accountHoldersRepository.save(aH);
    }
}
