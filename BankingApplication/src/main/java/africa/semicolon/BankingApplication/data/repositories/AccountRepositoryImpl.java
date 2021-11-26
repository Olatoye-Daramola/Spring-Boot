package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.*;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository{
    List<Account> accounts = new ArrayList<>();


    @Override
    public Account save(Account account) {
        accounts.add(account);
        return findAccountById(account.getCustomerId());
    }

    @Override
    public Account findAccountById(String id) {

        for (Account account : accounts) {
            if (account.getCustomerId().equalsIgnoreCase(id)) return account;
        }
        return null;
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

    @Override
    public void delete(String id) {
        Account account = findAccountById(id);
        delete(account);
    }


    @Override
    public List<Account> findAll() {
        return accounts;
    }


//    @Override
//    public List<Account> deleteAll(Account account) {
//        accounts.removeAll(account);
//        return accounts;
//    }
}
//MERN
//        mongo express   react   nodejs
//LAMP
//        linux angular mongo python