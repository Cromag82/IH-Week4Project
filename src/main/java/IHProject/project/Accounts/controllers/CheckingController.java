package IHProject.project.Accounts.controllers;

import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.services.CheckingService;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckingController {

    @Autowired
    CheckingService checkingService;

    @PutMapping("/setBalance/{id}/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getBalance(@PathVariable long id, @RequestParam Money money) {
        return checkingService.setBalance(id, money);
    }
}
