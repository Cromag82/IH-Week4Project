package IHProject.project;
import IHProject.project.AccountHolders.controllers.DTO.NewAccountDTO;
import IHProject.project.AccountHolders.controllers.DTO.SavingsDTO;
import IHProject.project.AccountHolders.controllers.DTO.TransferMoneyDTO;
import IHProject.project.AccountHolders.entities.Admin;
import IHProject.project.AccountHolders.entities.ThirdParty;
import IHProject.project.AccountHolders.repositories.AccountHoldersRepository;
import IHProject.project.AccountHolders.repositories.AdminRepository;
import IHProject.project.AccountHolders.repositories.ThirdPartyRepository;
import IHProject.project.Accounts.entities.CreditCard;
import IHProject.project.Accounts.enums.Status;
import IHProject.project.Accounts.repositories.CheckingRepository;
import IHProject.project.Accounts.repositories.CreditCardRepository;
import IHProject.project.Accounts.repositories.SavingsRepository;
import IHProject.project.Accounts.repositories.StudentCheckingRepository;
import IHProject.project.Transactions.entities.Transactions;
import IHProject.project.Transactions.repository.TransactionsRepository;
import IHProject.project.embeddables.Adress;
import IHProject.project.AccountHolders.entities.AccountHolders;
import IHProject.project.Accounts.entities.Checking;
import IHProject.project.embeddables.Money;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProjectApplicationTests {


	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	@Autowired
	AccountHoldersRepository accountHoldersRepository;
	@Autowired
	CheckingRepository checkingRepository;
	@Autowired
	StudentCheckingRepository studentCheckingRepository;
	@Autowired
	CreditCardRepository creditCardRepository;
	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	SavingsRepository savingsRepository;


	Checking accountTest;
	AccountHolders holdersTest;
	CreditCard cCTest;
	Admin adminTest;
	ThirdParty tpTest;
	Transactions transactionTest;
	@Test
	@BeforeEach
	void set_Up() throws Exception{
		holdersTest = new AccountHolders("nombre", "contraseña","Asaf", LocalDate.of(2010,05,15), new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"),new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"));
		accountHoldersRepository.save(holdersTest);
		accountTest = new Checking(0L, new Money(BigDecimal.valueOf(1000.00)), "secretKey", new Money(BigDecimal.valueOf(250.00)), new Money(BigDecimal.valueOf(12.00)), LocalDate.now(), Status.ACTIVE, holdersTest, holdersTest);
		checkingRepository.save(accountTest);
		cCTest = new CreditCard(new Money(BigDecimal.valueOf(10000.00)), holdersTest, new Money(BigDecimal.valueOf(50000.00)),BigDecimal.valueOf(0.25),new Money(BigDecimal.valueOf(40.00)));
		creditCardRepository.save(cCTest);
		adminTest = new Admin("prueba","prueba","prueba");
		adminRepository.save(adminTest);
		tpTest = new ThirdParty("hash","AsafTP");
		thirdPartyRepository.save(tpTest);
		transactionTest = new Transactions(accountTest);
		transactionsRepository.save(transactionTest);
	}

	@AfterEach
	public void eraseAll() {
		adminRepository.deleteAll();
		thirdPartyRepository.deleteAll();
		accountHoldersRepository.deleteAll();
		checkingRepository.deleteAll();
		studentCheckingRepository.deleteAll();
		creditCardRepository.deleteAll();
		transactionsRepository.deleteAll();
	}

	//AccountHolders Tests
	@Test
	public void accountHolder_newAccount_Ok() {
		assertEquals(true,accountHoldersRepository.findAll().size() == 1);

	}
	@Test
	public void getBalance_Ok() {
		// failing digits, given amount is matching.
		assertEquals(new BigDecimal("1000.00"), checkingRepository.findById(1L).get().getBalance());
	}
	@Test
	public void getCreditCardBalance_Ok() {
		// failing digits, given amount is matching.
		assertEquals(new BigDecimal("10000.00"), creditCardRepository.findById(1L).get().getBalance());

	}

	@Test
	public void transfer_Ok() throws Exception {
		Checking accountTest2 = new Checking(0L, new Money(BigDecimal.valueOf(1000.00)), "secretKey", new Money(BigDecimal.valueOf(250.00)), new Money(BigDecimal.valueOf(12.00)), LocalDate.now(), Status.ACTIVE, holdersTest, holdersTest);
		checkingRepository.save(accountTest2);
		TransferMoneyDTO tDto = new TransferMoneyDTO(1,2,200.00,"Pepe");
		String body = objectMapper.writeValueAsString(tDto);

		MvcResult mvcResult = mockMvc.perform(put("/transfer/").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();


		assertEquals(new BigDecimal("1200.00"),checkingRepository.findById(2L).get().getBalance());

	}

	//Admin Tests

	@Test
	public void newAdmin_Ok() throws Exception {
		Admin tester = new Admin("prueba","prueba","prueba2");
		String body = objectMapper.writeValueAsString(tester);

		MvcResult mvcResult = mockMvc.perform(post("/newAdmin").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		assertTrue(mvcResult.getResponse().getContentAsString().contains("prueba2"));
		assertEquals("prueba2",adminRepository.findByName("prueba2").get().getName());

	}

	@Test
	public void newChecking_Account_ok() throws Exception{
		NewAccountDTO naDTO = new NewAccountDTO(new Money(BigDecimal.valueOf(2000.00)),"secretKey",LocalDate.now(),holdersTest);
		String body = objectMapper.writeValueAsString(naDTO);
		adminRepository.save(adminTest);
		MvcResult mvcResult = mockMvc.perform(post("/newAccount/2/checking" ).content(body).contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isCreated()).andReturn();

		assertThat(mvcResult.getResponse().getContentAsString().contains("secretKey"));

	}

	//doesn´t work with cascade, nor without it!!! This is because classes are parent-child relationship
	@Test
	public void newStudent_Account_ok() throws Exception {
		AccountHolders holdersTest2 = new AccountHolders("nombre", "contraseña","Asaf", LocalDate.of(2010,05,15), new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"),new Adress("Cadi", "3", "8320L", "Dosrius", "Dis1"));
		NewAccountDTO naDTO = new NewAccountDTO(new Money(BigDecimal.valueOf(2000.00)),"secretKey",LocalDate.now(),holdersTest2);
		String body = objectMapper.writeValueAsString(naDTO);
		adminRepository.save(adminTest);
		MvcResult mvcResult = mockMvc.perform(post("/newAccount/2/checking" ).content(body).contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isCreated()).andReturn();

		assertThat(mvcResult.getResponse().getContentAsString().contains("student"));
		assertEquals(1, studentCheckingRepository.findAll().size() == 1);

	}
	//same issue as above;
	//doesn´t work with cascade, nor without it!!! This is because classes are parent-child relationship
	@Test
	public void newSaving_Account_Ok() throws Exception {
		SavingsDTO testSavingDTO = new SavingsDTO(new Money(BigDecimal.valueOf(1000.00)),"secretKey",LocalDate.now(),holdersTest,holdersTest,new BigDecimal("0.1"));
		String body = objectMapper.writeValueAsString(testSavingDTO);
		MvcResult mvcResult = mockMvc.perform(post("/newAccount/1/savings" ).content(body).contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isCreated()).andReturn();

		assertThat(mvcResult.getResponse().getContentAsString().contains("secretKey"));
		assertEquals(1,savingsRepository.findAll().size());

	}
	//same problem, can´t test
	@Test
	public void setBalance_Ok() throws Exception {

		String body = objectMapper.writeValueAsString("1" + new Money(BigDecimal.valueOf(2000.00)));
		MvcResult mvcResult = mockMvc.perform(put("/AccountBalance/" + checkingRepository.findById(Long.valueOf("1")).get().getId()).content(body).contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isAccepted()).andReturn();

		assertThat(mvcResult.getResponse().getContentAsString().contains("2000.00"));
		Assertions.assertThat(checkingRepository.findById(Long.valueOf("1")).get().getBalance().equals("2000.00"));


	}























}
