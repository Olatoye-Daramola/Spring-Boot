package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankRepositoryImplTest {
    BankRepository bankRepository;

    @BeforeEach
    void setUp() {
        bankRepository = new BankRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Bank bank = new Bank("1234567");
        bank.setName("Lois");
        Bank banking = bankRepository.save(bank);
        assertEquals(bank, banking);
    }

    @Test
    void findById() {
        Bank bank = new Bank("1234567");
        bank.setName("Lois");
        bankRepository.save(bank);

        Bank news = bankRepository.findBankById("1234567");
        assertEquals(bank, news);
    }

    @Test
    void delete() {
        Bank bank = new Bank("1234567");
        bank.setName("Lois");
        bankRepository.save(bank);
        bankRepository.findBankById("1234567");

        bankRepository.delete(bank);
        assertNull(bankRepository.findBankById("1234567"));
    }

    @Test
    void deleteById() {
        Bank bank = new Bank("1234567");
        bank.setName("Lois");
        bankRepository.save(bank);
        bankRepository.findBankById("1234567");

        bankRepository.delete("1234567");
        assertNull(bankRepository.findBankById("1234567"));
    }

    @Test
    void findAll() {
        Bank bank = new Bank("1234567");
        bank.setName("Lois");
        bankRepository.save(bank);
        bankRepository.save(bank);
        bankRepository.save(bank);
        bankRepository.save(bank);
        bankRepository.save(bank);

        assertEquals(5, bankRepository.findAll().size());
    }
}