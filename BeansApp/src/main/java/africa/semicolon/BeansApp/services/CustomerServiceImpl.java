package africa.semicolon.BeansApp.services;

import africa.semicolon.BeansApp.data.models.Customer;
import africa.semicolon.BeansApp.data.repositories.CustomerRepository;
import africa.semicolon.BeansApp.data.repositories.CustomerRepositoryImpl;
import africa.semicolon.BeansApp.dtos.requests.RegisterCustomerRequest;
import africa.semicolon.BeansApp.dtos.responses.RegisterCustomerResponse;
import africa.semicolon.BeansApp.exceptions.CustomerNotFoundException;
import africa.semicolon.BeansApp.exceptions.DuplicateCustomerException;
import africa.semicolon.BeansApp.utils.ModelMapper;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private static final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest customerRequest) {
        Customer customer = ModelMapper.map(customerRequest);
        RegisterCustomerResponse response = new RegisterCustomerResponse();
        try {
            Optional<Customer> customerInDatabase = customerRepository.findCustomer(customer);
            if (customerInDatabase.isPresent())
                throw new DuplicateCustomerException(customerRequest.getCustomerEmail() + " already exists");
        }
        catch (CustomerNotFoundException error) {
            Customer savedCustomer = customerRepository.saveCustomer(customer);
            response = ModelMapper.map(savedCustomer);
        }
        return response;
    }

    @Override
    public Optional<Customer> getCustomer(Customer customer) {
        return customerRepository.findCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    @Override
    public void updateCustomerPassword(Customer customer, String newPassword) {
        customerRepository.changeCustomerPassword(customer, newPassword);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAllCustomers();
    }
}
