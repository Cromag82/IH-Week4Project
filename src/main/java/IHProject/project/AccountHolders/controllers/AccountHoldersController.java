package IHProject.project.AccountHolders.controllers;

import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.AccountHolders.services.AccountHoldersService;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountHoldersController {

    @Autowired
    AccountHoldersService accountHoldersService;

    @PostMapping("/newAccount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolders newAHAccount(@RequestBody AccountHolders aH) {
        return accountHoldersService.newAHAccount(aH);
    }

    @GetMapping("/balanceAccount/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getBalance (@PathVariable long id) {
        return accountHoldersService.getBalance(id);
    }


    @GetMapping("/balanceCC2/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getCCBalance (@PathVariable long id) {
        return accountHoldersService.getCCBalance(id);
    }

    @PutMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money transferFunds(@PathVariable(name = "id") long idHolder, @RequestParam long idDest, @RequestParam Money money, @RequestParam String name) throws Exception, ResponseStatusException {
        return accountHoldersService.transferFunds(idHolder,idDest,money,name);
    }






}
