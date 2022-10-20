package IHProject.project.AccountHolders.services;

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

@Service
public class AccountHoldersService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    public Money getBalance(long id) {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        return checkingRepository.findById(id).get().getBalance();
    }

    public Money getCCBalance(long id) {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        return creditCardRepository.findById(id).get().getBalance();
    }

    //el name no tiene funcionalidad en el método, dejar para seguridad ??
    public Money transferFunds(long idHolder, long idDest, Money money, String name) throws Exception, ResponseStatusException{
        checkingRepository.findById(idHolder).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Holder Account not found"));
        checkingRepository.findById(idDest).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Destination Account not found"));
        Checking holder = checkingRepository.findById(idHolder).get();
        Checking dest = checkingRepository.findById(idDest).get();
        if (holder.getBalance().getAmount().compareTo(money.getAmount()) == 1) {
            holder.setBalance(new Money(holder.getBalance().getAmount().subtract(money.getAmount())));
            dest.setBalance(new Money(dest.getBalance().getAmount().add(money.getAmount())));
            checkingRepository.save(holder);
            checkingRepository.save(dest);
            return checkingRepository.findById(idDest).get().getBalance();
        } else throw new Exception("Not enough funds");
    }

    public AccountHolders newAHAccount(AccountHolders aH) {
        return accountHoldersRepository.save(aH);
    }
}
