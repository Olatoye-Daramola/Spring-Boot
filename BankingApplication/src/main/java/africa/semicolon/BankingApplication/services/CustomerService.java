package africa.semicolon.BankingApplication.services;

import africa.semicolon.BankingApplication.data.models.Customer;
import africa.semicolon.BankingApplication.dtos.responses.CustomerResponse;

import java.util.List;

public interface CustomerService {

//    String createAccount(String firstName, String lastName);
//    String createAccount(String firstName, String lastName, String bvn);

//    List<Customer> findAll();
    List<CustomerResponse> findAll();

    void addNew(Customer customer);
}
