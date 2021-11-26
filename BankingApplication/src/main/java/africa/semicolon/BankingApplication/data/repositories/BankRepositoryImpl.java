package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {
    List<Bank> banks = new ArrayList<>();


    @Override
    public Bank save(Bank bank) {
        Bank foundBank = findBankById(bank.getId());
        if (foundBank != null) {
            delete(foundBank);
        }
        banks.add(bank);
        return findBankById(bank.getId());
    }

    @Override
    public Bank findBankById(String id) {
        for(Bank bank : banks) {
            if(bank.getId().equalsIgnoreCase(id)) return bank;
        }
        return null;
    }

    @Override
    public void delete(Bank bank) {
        banks.remove(bank);
    }

    @Override
    public void delete(String id) {
        Bank bank = findBankById(id);
        banks.remove(bank);
    }

    @Override
    public List<Bank> findAll() {
        return banks;
    }
}
