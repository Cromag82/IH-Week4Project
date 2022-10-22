package IHProject.project.AccountHolders.controllers;

import IHProject.project.AccountHolders.controllers.DTO.TransferMoneyDTO;
import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.AccountHolders.services.AccountHoldersService;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

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
    public BigDecimal getBalance (@PathVariable long id) {
        return accountHoldersService.getBalance(id);
    }


    @GetMapping("/balanceCC2/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal getCCBalance (@PathVariable long id) {
        return accountHoldersService.getCCBalance(id);
    }

    @PutMapping("/transfer/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal transferFunds(@RequestBody TransferMoneyDTO transfer) throws Exception, ResponseStatusException {
        return accountHoldersService.transferFunds(transfer);
    }






}
