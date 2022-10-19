package IHProject.project.AccountHolders.services;

import IHProject.project.AccountHolders.repositories.ThirdPartyRepository;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.embeddables.Money;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AdminService adminService;

    public void transferFunds(String hashedKey, Money money, long idDest, String secretKey) {
        checkingRepository.findById(idDest).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        Checking temp = checkingRepository.findById(idDest).get();
        if (thirdPartyRepository.findByHashedKey(hashedKey).isPresent() && (checkingRepository.findById(idDest).get().getSecreKey().equals(secretKey))) {
            temp.setBalance(new Money(temp.getBalance().getAmount().add(money.getAmount())));
            checkingRepository.save(temp);
        }

    }


}
