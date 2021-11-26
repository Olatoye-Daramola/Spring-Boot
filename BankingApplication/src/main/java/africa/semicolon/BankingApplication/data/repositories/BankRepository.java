package africa.semicolon.BankingApplication.data.repositories;

//import africa.semicolon.BankingApplication.data.models.Account;
import africa.semicolon.BankingApplication.data.models.Bank;

import java.util.List;

public interface BankRepository {

   Bank save (Bank bank);

   Bank findBankById(String id);

   void delete (Bank bank);

   void delete(String id);

   List<Bank> findAll();
}
