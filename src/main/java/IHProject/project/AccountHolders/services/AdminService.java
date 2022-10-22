package IHProject.project.AccountHolders.services;

import IHProject.project.AccountHolders.controllers.DTO.CreditCardDTO;
import IHProject.project.AccountHolders.controllers.DTO.SavingsDTO;
import IHProject.project.AccountHolders.controllers.DTO.NewAccountDTO;
import IHProject.project.AccountHolders.controllers.DTO.ThirdPartyDTO;
import IHProject.project.AccountHolders.entities.Admin;
import IHProject.project.AccountHolders.entities.ThirdParty;
import IHProject.project.AccountHolders.repositories.AdminRepository;
import IHProject.project.AccountHolders.repositories.ThirdPartyRepository;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.Accounts.entities.Savings;
import IHProject.project.Accounts.entities.StudentChecking;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.Accounts.repositories.SavingsRepository;
import IHProject.project.Accounts.repositories.StudentCheckingRepository;
import IHProject.project.embeddables.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Checking saveCheckingAccount (long id, NewAccountDTO dto) throws Exception {
        if (adminRepository.findById(id).isPresent() && ((LocalDate.now().getYear() - dto.getPrimaryOwner().getBirthDate().getYear())) >= 24 ) {
        Checking newAccount = new Checking(
                dto.getBalance(),
                dto.getCreationDate(),
                dto.getSecreKey(),
                dto.getPrimaryOwner());
        return checkingRepository.save(newAccount);
        } else if (adminRepository.findById(id).isPresent() && ((LocalDate.now().getYear() - dto.getPrimaryOwner().getBirthDate().getYear()) < 24 )) {
            StudentChecking newAccount = new StudentChecking(
                    dto.getBalance(),
                    dto.getCreationDate(),
                    dto.getSecreKey(),
                    dto.getPrimaryOwner());
            studentCheckingRepository.save(newAccount);
        }
        throw new Exception("Admin not found");
    }

    public Savings saveSavingsAccount (long id, SavingsDTO sDTO) throws Exception {
        if (adminRepository.findById(id).isPresent()) {
            Savings newSavings = new Savings(
                sDTO.getBalance(),
                sDTO.getCreationDate(),
                sDTO.getInterestRate(),
                sDTO.getCheckingPrimaryOwner(),
                sDTO.getCheckingSecOwner(),
                sDTO.getSecreKey());
        return savingsRepository.save(newSavings);}
        else throw new Exception("Admin not found");
    }

    public CreditCard saveCreditCardAccount (long id, CreditCardDTO dto) throws Exception {
        if (adminRepository.findById(id).isPresent()) {
            CreditCard newCC = new CreditCard(
                dto.getBalance(),
                dto.getCreditCardPrimaryOwner(),
                dto.getCreditLimit(),
                dto.getInterestRate(),
                dto.getPenaltyFee()
        );
        return creditCardRepository.save(newCC);}
        else throw new Exception("Admin not found");
    }

    public BigDecimal getBalance(long id) throws ResponseStatusException {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found"));
        return checkingRepository.findById(id).get().getBalance();
    }

    public BigDecimal getCCBalance(long id) throws ResponseStatusException {
        creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        return creditCardRepository.findById(id).get().getBalance();
    }

    public void setAccountBalance(long id, Money money) {
        checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found"));
        checkingRepository.findById(id).get().setBalance(money);
    }

    public void setCCBalance(long id, Money money) {
        creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account not found"));
        creditCardRepository.findById(id).get().setBalance(money);
    }

    public ThirdParty saveNewThirdParty(long id, ThirdPartyDTO tp) throws Exception {
        adminRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Admin not found"));
        if (!thirdPartyRepository.findByHashedKey(tp.getHashedKey()).isPresent()) {
            ThirdParty newTP = new ThirdParty(tp.getHashedKey(),tp.getName());
            return thirdPartyRepository.save(newTP);
        } else throw new Exception("ThirdParty already existing");
    }

    public Admin newAdmin(Admin admin) throws ResponseStatusException{
        if (adminRepository.findByName(admin.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Admin already present");
        }
        passwordEncoder.encode(admin.getPassword());
        return adminRepository.save(admin);
    }
}
