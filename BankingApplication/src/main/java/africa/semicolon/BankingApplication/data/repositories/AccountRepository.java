package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.Account;

import java.util.List;

public interface AccountRepository {

    Account save(Account account);
    Account findAccountById(String id);
    void delete(Account account);
    void delete(String id);
    List<Account> findAll();
//    List<Account> deleteAll(Account account);

}
