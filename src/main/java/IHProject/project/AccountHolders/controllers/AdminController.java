package IHProject.project.AccountHolders.controllers;

import IHProject.project.AccountHolders.controllers.DTO.CreditCardDTO;
import IHProject.project.AccountHolders.controllers.DTO.NewAccountDTO;
import IHProject.project.AccountHolders.controllers.DTO.SavingsDTO;
import IHProject.project.AccountHolders.services.AdminService;
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
    public void createNewAccount(@PathVariable long id, @RequestBody SavingsDTO naDTO) throws Exception {
        adminService.saveSavingsAccount(id, naDTO);
    }
    @PostMapping("/newAccount/{id}/creditcard")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewAccount (@PathVariable long id, @RequestBody CreditCardDTO naDTO) throws Exception {
        adminService.saveCreditCardAccount(id, naDTO);

    }
}