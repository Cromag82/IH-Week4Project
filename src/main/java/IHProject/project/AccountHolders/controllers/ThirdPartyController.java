package IHProject.project.AccountHolders.controllers;

import IHProject.project.AccountHolders.services.ThirdPartyService;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PutMapping("/sendMoneyTPU/{hashedKey}")
    @ResponseStatus(HttpStatus.ACCEPTED)
        public void sendMoney (@PathVariable(name="hashedKey") String hashedKey,
                               @RequestParam Money money,
                               @RequestParam long idDest,
                               @RequestParam String secretKey) {
        thirdPartyService.transferFunds(hashedKey, money, idDest, secretKey);
    }

}
