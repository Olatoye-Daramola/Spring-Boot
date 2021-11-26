package africa.semicolon.BankingApplication.services;

import africa.semicolon.BankingApplication.dtos.requests.CreateAccountRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static africa.semicolon.BankingApplication.data.models.AccountType.CURRENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BankServiceImplTest {
    BankService bankService;

    @BeforeEach
    void setUp() {
        bankService = new BankServiceImpl();
    }

    @AfterEach
    void tearDown() {
        bankService = null;
    }

    @Test
    void createBank() {
        String bankId = bankService.createBank("GTCO");

        assertEquals("01", bankId);
    }

    @Test
    void createTwoBanks_secondBankWillBe02() {
        String gtCoId = bankService.createBank("GTCO");
        String firstBankId = bankService.createBank("FIRST Bank");

        assertEquals("01", gtCoId);
        assertEquals("02", firstBankId);
    }

    @Test
    void createTwoBanks_repositoryShouldHave2Banks() {
        String gtCoId = bankService.createBank("GTCO");
        String firstBankId = bankService.createBank("FIRST Bank");

        assertEquals("01", gtCoId);
        assertEquals("02", firstBankId);
        assertEquals(2, bankService.findAllBanks().size());
    }

    @Test
    void bankCanCreateAccountForCustomers_test() {
        String gtCoId = bankService.createBank("GTCO");
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountType(CURRENT);
        createAccountRequest.setBankId("01");
        createAccountRequest.setFirstName("Agba");
        createAccountRequest.setLastName("Mon-shure");
        String accountNumber = bankService.createAccount(createAccountRequest);

        assertEquals("0100000001", accountNumber);
    }

    @Test
    void createTwoAccountsInBank1_accountNumberShouldBe02() {
        String gtCoId = bankService.createBank("GTCO");
        CreateAccountRequest agbaForm = new CreateAccountRequest();
        agbaForm.setAccountType(CURRENT);
        agbaForm.setBankId("01");
        agbaForm.setFirstName("Agba");
        agbaForm.setLastName("Mon-shure");
        String agbaAccountNumber = bankService.createAccount(agbaForm);

        CreateAccountRequest ajohnneForm = new CreateAccountRequest();
        ajohnneForm.setAccountType(CURRENT);
        ajohnneForm.setBankId("01");
        ajohnneForm.setFirstName("Ajohnne");
        ajohnneForm.setLastName("LogicOverflow");
        String ajohnneAccountNumber = bankService.createAccount(ajohnneForm);

        assertEquals("0100000001", agbaAccountNumber);
        assertEquals("0100000002", ajohnneAccountNumber);
    }

    @Test
    void createTwoAccountsInBank1_andOneAccountsInBank2_accountNumberShouldBe01() {
        String gtCoId = bankService.createBank("GTCO");
        String secondBankId = bankService.createBank("First Bank");

        CreateAccountRequest agbaForm = new CreateAccountRequest();
        agbaForm.setAccountType(CURRENT);
        agbaForm.setBankId(gtCoId);
        agbaForm.setFirstName("Agba");
        agbaForm.setLastName("Mon-shure");
        String agbaAccountNumber = bankService.createAccount(agbaForm);

        CreateAccountRequest ajohnneForm = new CreateAccountRequest();
        ajohnneForm.setAccountType(CURRENT);
        ajohnneForm.setBankId(gtCoId);
        ajohnneForm.setFirstName("Ajohnne");
        ajohnneForm.setLastName("LogicOverflow");
        String ajohnneAccountNumber = bankService.createAccount(ajohnneForm);

        CreateAccountRequest ajerryForm = new CreateAccountRequest();
        ajerryForm.setAccountType(CURRENT);
        ajerryForm.setBankId(secondBankId);
        ajerryForm.setFirstName("Ajerry");
        ajerryForm.setLastName("BigFowl");
        String ajerryAccountNumber = bankService.createAccount(ajerryForm);

        assertEquals("0100000001", agbaAccountNumber);
        assertEquals("0100000002", ajohnneAccountNumber);
        assertEquals("0200000001", ajerryAccountNumber);
    }

    @Test
    void whenAccountIsCreated_CustomerIsCreatedToo() {
        String gtCoId = bankService.createBank("GTCO");

        CreateAccountRequest agbaForm = new CreateAccountRequest();
        agbaForm.setAccountType(CURRENT);
        agbaForm.setBankId(gtCoId);
        agbaForm.setFirstName("Agba");
        agbaForm.setLastName("Mon-shure");
        String agbaAccountNumber = bankService.createAccount(agbaForm);

        CustomerService customerService = new CustomerServiceImpl();
        assertEquals(1, customerService.findAll().size());
    }
}
