package IHProject.project.AccountHolders.controllers;

import IHProject.project.AccountHolders.controllers.DTO.CreditCardDTO;
import IHProject.project.AccountHolders.controllers.DTO.NewAccountDTO;
import IHProject.project.AccountHolders.controllers.DTO.SavingsDTO;
import IHProject.project.AccountHolders.controllers.DTO.ThirdPartyDTO;
import IHProject.project.AccountHolders.entities.ThirdParty;
import IHProject.project.AccountHolders.services.AdminService;
import IHProject.project.AccountHolders.services.ThirdPartyService;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/newAccount/{id}/checking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewAccount(@PathVariable long id, @RequestBody NewAccountDTO naDTO) throws Exception {
        adminService.saveCheckingAccount(id, naDTO);
    }
    @PostMapping("/newAccount/{id}/savings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewSavingAccount(@PathVariable long id, @RequestBody SavingsDTO naDTO) throws Exception {
        adminService.saveSavingsAccount(id, naDTO);
    }
    @PostMapping("/newAccount/{id}/creditcard")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewAccount (@PathVariable long id, @RequestBody CreditCardDTO naDTO) throws Exception {
        adminService.saveCreditCardAccount(id, naDTO);

    }

    @GetMapping("/AccountBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getBalance (@PathVariable long id) {
       return adminService.getBalance(id);
    }

    @PostMapping("/AccountBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setAccountBalance (@PathVariable long id, @RequestParam Money money) {
        adminService.setAccountBalance(id, money);
    }

    @GetMapping("/CreditCardBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getCCBalance (@PathVariable long id) {
        return adminService.getCCBalance(id);
    }

    @PostMapping("/CreditCardBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setCCBalance (@PathVariable long id, @RequestParam Money money) {
        adminService.setCCBalance(id, money);
    }

    @PostMapping("/createThirdPartyUser/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty newThirdParty(@PathVariable long id, @RequestBody ThirdPartyDTO tp) throws Exception {
        return adminService.saveNewThirdParty(id, tp);
    }




}