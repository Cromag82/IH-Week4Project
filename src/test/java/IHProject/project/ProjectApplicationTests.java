package IHProject.project;
import IHProject.project.AccountHolders.repositories.AccountHoldersRepository;
import IHProject.project.AccountHolders.repositories.AdminRepository;
import IHProject.project.AccountHolders.repositories.ThirdPartyRepository;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.Transactions.repository.TransactionsRepository;
import IHProject.project.embeddables.Adress;
import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.embeddables.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	@Autowired
	AccountHoldersRepository accountHoldersRepository;
	@Autowired
	CheckingRepository checkingRepository;
	@Autowired
	CreditCardRepository creditCardRepository;
	@Autowired
	TransactionsRepository transactionsRepository;

//	ProjectApplicationTests() throws Exception { }

	@Test
	@BeforeEach
	void set_Up() throws Exception{
		AccountHolders holdersTest = new AccountHolders("Asaf", LocalDate.of(1982,05,15), new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"),new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"));
		accountHoldersRepository.save(holdersTest);
		Checking accountTest = new Checking(new Money(BigDecimal.valueOf(1000.00)), LocalDate.now(),"secret", holdersTest);
	/*	CreditCard cCTest = new CreditCard(new Money(BigDecimal.valueOf(10000.00)), holdersTest, new Money(BigDecimal.valueOf(50000.00)),BigDecimal.valueOf(0.25),new Money(BigDecimal.valueOf(40.00)));
		Admin adminTest = new Admin("AsafAdmin");
		ThirdParty tpTest = new ThirdParty("hash","AsafTP");
		Transactions transactionTest = new Transactions(accountTest);
	*/
		checkingRepository.save(accountTest);
	/*	creditCardRepository.save(cCTest);
		adminRepository.save(adminTest);
		thirdPartyRepository.save(tpTest);
		transactionsRepository.save(transactionTest); */
	}



}
