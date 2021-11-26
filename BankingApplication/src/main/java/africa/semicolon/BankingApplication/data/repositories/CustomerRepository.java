package africa.semicolon.BankingApplication.data.repositories;

import africa.semicolon.BankingApplication.data.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findCustomerById(String id);
    void delete(Customer customer);
    void delete(String id);
    List<Customer> findAll();
}
