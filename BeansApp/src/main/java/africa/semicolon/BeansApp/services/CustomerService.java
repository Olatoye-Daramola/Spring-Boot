package africa.semicolon.BeansApp.services;

import africa.semicolon.BeansApp.data.models.Customer;
import africa.semicolon.BeansApp.dtos.requests.RegisterCustomerRequest;
import africa.semicolon.BeansApp.dtos.responses.RegisterCustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest customerRequest);
    Optional<Customer> getCustomer(Customer customer);
    Optional<Customer> getCustomerByEmail(String email);
    List<Customer> findAllCustomers();
    void updateCustomerPassword(Customer customer, String newPassword);
    void deleteCustomer(Customer customer);
    void deleteAllCustomers();
}
