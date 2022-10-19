package IHProject.project.Accounts.services;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    public Money setBalance(long id, Money money) throws ResponseStatusException{
        checkingRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return checkingRepository.findById(id).get().setBalance(money);
    }
}
