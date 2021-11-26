package africa.semicolon.BeansApp.data.repositories;

import africa.semicolon.BeansApp.data.models.Customer;
import africa.semicolon.BeansApp.exceptions.CustomerNotFoundException;
import africa.semicolon.BeansApp.exceptions.DuplicateCustomerException;

import java.util.*;

public class CustomerRepositoryImpl implements CustomerRepository{
    Map<String, Customer> database = new HashMap<>();

    @Override
    public Customer saveCustomer(Customer customer) {
        if (database.containsKey(customer.getEmail())) throw new DuplicateCustomerException("Sorry, customer already exists");
        database.put(customer.getEmail(), customer);
        return database.get(customer.getEmail());
    }

    @Override
    public void changeCustomerPassword(Customer customer, String password) {
        assert(findCustomer(customer).isPresent());
        Customer foundCustomer = findCustomer(customer).get();
        foundCustomer.setPassword(password);
    }

    @Override
    public Optional<Customer> findCustomer(Customer customer) {
        assert(findCustomerByEmail(customer.getEmail()).isPresent());
        return Optional.of(findCustomerByEmail(customer.getEmail()).get());
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        if(!(database.containsKey(email))) throw new CustomerNotFoundException("Customer cannot be found");
        return Optional.of(database.get(email));
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customersList = new ArrayList<>();
        Set<String> keys = database.keySet();
        for (String key : keys) {
            customersList.add(database.get(key));
        }
        return customersList;
    }

    @Override
    public void deleteCustomerByEmail(String email) {
        database.remove(email);

    }

    @Override
    public void deleteCustomer(Customer customer) {
        deleteCustomerByEmail(customer.getEmail());
    }

    @Override
    public void deleteAllCustomers() {
        database.clear();
    }
}
