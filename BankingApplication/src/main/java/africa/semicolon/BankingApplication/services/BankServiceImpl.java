package africa.semicolon.BankingApplication.services;

import africa.semicolon.BankingApplication.data.models.Account;
import africa.semicolon.BankingApplication.data.models.Bank;
import africa.semicolon.BankingApplication.data.models.Customer;
import africa.semicolon.BankingApplication.data.repositories.BankRepository;
import africa.semicolon.BankingApplication.data.repositories.BankRepositoryImpl;
import africa.semicolon.BankingApplication.dtos.requests.CreateAccountRequest;

import java.util.List;

public class BankServiceImpl implements BankService {
    private BankRepository bankRepository = new BankRepositoryImpl();
    private CustomerService customerService = new CustomerServiceImpl();
//    private static int lastAccountNumber = 0;
//    private static int lastBankIdCreated = 0;

    @Override
    public String createBank(String bankName) {
        String bankId = generateBankId();
        Bank bank = new Bank(bankId);
        bank.setName(bankName);
        Bank savedBank = bankRepository.save(bank);
        return savedBank.getId();
    }

    private String generateBankId() {
        int lastBankIdCreated = findAllBanks().size();
        return String.format("%02d", ++lastBankIdCreated);
    }

    @Override
    public List<Bank> findAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public String createAccount(CreateAccountRequest createAccountRequest) {
        String bankId = createAccountRequest.getBankId();
        String accountNumber = generateSuffixFor(bankId);
        Customer customer = new Customer();
        Account account = new Account();
        account.setNumber(bankId + accountNumber);
        account.setType(createAccountRequest.getAccountType());

        customer.setFirstName(createAccountRequest.getFirstName());
        customer.setLastName(createAccountRequest.getLastName());
        customer.getAccounts().add(account);
        customer.setBvn("No bvn implemented yet");
        customerService.addNew(customer);

        Bank bank = bankRepository.findBankById(bankId);
        bank.getAccounts().add(account);
        bankRepository.save(bank);
        return account.getNumber();
    }

    private String generateSuffixFor(String bankId) {
        Bank bank = bankRepository.findBankById(bankId);
        int lastNumber = bank.getAccounts().size();
        return String.format("%08d", ++lastNumber);
    }
}
