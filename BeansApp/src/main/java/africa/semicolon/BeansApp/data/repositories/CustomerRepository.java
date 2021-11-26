package africa.semicolon.BeansApp.data.repositories;

import africa.semicolon.BeansApp.data.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer saveCustomer(Customer customer);
    void changeCustomerPassword(Customer customer, String password);
    Optional<Customer> findCustomer(Customer customer);
    Optional<Customer> findCustomerByEmail(String email);
    List<Customer> findAllCustomers();
    void deleteCustomerByEmail(String email);
    void deleteCustomer(Customer customer);
    void deleteAllCustomers();
}
