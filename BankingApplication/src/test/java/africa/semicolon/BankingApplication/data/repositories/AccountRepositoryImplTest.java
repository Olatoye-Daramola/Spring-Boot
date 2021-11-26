package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        AccountRepositoryImpl imp = new AccountRepositoryImpl();
        Account account = new Account();
//        Customer customer = new Customer();
//        customer.setFirstName("Ama");
//        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));

        assertEquals(account,imp.save(account));
    }

    @Test
    void findById() {
        AccountRepositoryImpl imp = new AccountRepositoryImpl();
        Account account = new Account();
        Customer customer = new Customer();
        customer.setFirstName("Ama");
        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));
        Account acc = imp.save(account);

        assertEquals(account, imp.findAccountById("12345"));
    }

    @Test
    void delete() {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setFirstName("Ama");
        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));
        accountRepository.save(account);
        assertNotNull(accountRepository.findAccountById("12345"));

        accountRepository.delete(account);
        assertNull(accountRepository.findAccountById("12345"));
    }

    @Test
    void testDelete() {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setFirstName("Ama");
        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));
        accountRepository.save(account);
        assertNotNull(accountRepository.findAccountById("12345"));

        accountRepository.delete("12345");
        assertNull(accountRepository.findAccountById("12345"));
    }

    @Test
    void findAll() {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setFirstName("Ama");
        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);
        assertEquals(4, accountRepository.findAll().size());
    }

    @Test
    void deleteAll() {
        Account account = new Account();
        Customer customer = new Customer();
        customer.setFirstName("Ama");
        customer.setFirstName("Lois");
        account.setCustomerId("12345");
        account.setNumber("234543");
        account.setType(AccountType.SAVINGS);
        account.setBalance(new BigDecimal ("234.65"));
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);
//        assertEquals(0, accountRepository.deleteAll(account).size());
    }
}