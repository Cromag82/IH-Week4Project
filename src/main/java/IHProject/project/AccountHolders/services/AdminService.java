package IHProject.project.AccountHolders.services;

import IHProject.project.AccountHolders.controllers.DTO.CreditCardDTO;
import IHProject.project.AccountHolders.controllers.DTO.SavingsDTO;
import IHProject.project.AccountHolders.controllers.DTO.NewAccountDTO;
import IHProject.project.AccountHolders.repositories.AdminRepository;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.Accounts.entities.Savings;
import IHProject.project.Accounts.entities.StudentChecking;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.Accounts.repositories.SavingsRepository;
import IHProject.project.Accounts.repositories.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
