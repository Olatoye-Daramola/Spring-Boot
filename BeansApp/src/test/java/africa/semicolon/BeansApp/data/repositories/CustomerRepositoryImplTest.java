package africa.semicolon.BeansApp.data.repositories;

import africa.semicolon.BeansApp.data.models.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }
    public Customer saveCustomerHelperMethod() {
        Customer customer = new Customer();
        customer.setEmail("mrchibuzo@semicolon.africa");
        customer.setFullName("Uncle Chibob");
        customer.setPassword("semicolon01");

        return customerRepository.saveCustomer(customer);
    }

    @Test
    void saveCustomer() {
        saveCustomerHelperMethod();
        assertEquals(1, customerRepository.findAllCustomers().size());
        assertEquals("mrchibuzo@semicolon.africa",
                customerRepository.findAllCustomers().get(0).getEmail()
        );
    }

    @Test
    void changeCustomerPassword() {
        Customer savedCustomer = saveCustomerHelperMethod();
        String newPassword = "0123456789";
        customerRepository.changeCustomerPassword(savedCustomer, newPassword);
        assert(customerRepository.findCustomer(savedCustomer).isPresent());
        assertEquals(newPassword, customerRepository.findCustomer(savedCustomer).get().getPassword());
    }

    @Test
    void findCustomer() {
        Customer savedCustomer = saveCustomerHelperMethod();
        Optional<Customer> confirmSavedCustomer = customerRepository.findCustomer(savedCustomer);
        assertTrue(confirmSavedCustomer.isPresent());
        assertEquals(savedCustomer, confirmSavedCustomer.get());
    }

    @Test
    void findCustomerByEmail() {
        Customer savedCustomer = saveCustomerHelperMethod();
        Optional<Customer> confirmSavedCustomer = customerRepository.findCustomerByEmail(savedCustomer.getEmail());
        assertTrue(confirmSavedCustomer.isPresent());
        assertEquals(savedCustomer, confirmSavedCustomer.get());
    }

    @Test
    void findAllCustomers() {
        Customer firstCustomer = new Customer();
        firstCustomer.setEmail("mrchibuzo@semicolon.africa");
        firstCustomer.setFullName("Uncle Chibob");
        firstCustomer.setPassword("semicolon01");
        customerRepository.saveCustomer(firstCustomer);

        Customer secondCustomer = new Customer();
        secondCustomer.setEmail("mrgabriel@semicolon.africa");
        secondCustomer.setFullName("Enum Master");
        secondCustomer.setPassword("semicolon02");
        customerRepository.saveCustomer(secondCustomer);

        assertEquals(2, customerRepository.findAllCustomers().size());
        assertEquals("mrchibuzo@semicolon.africa",
                customerRepository.findAllCustomers().get(1).getEmail()
        );
        assertEquals("mrgabriel@semicolon.africa",
                customerRepository.findAllCustomers().get(0).getEmail()
        );
    }

    @Test
    void deleteCustomerByEmail() {
        Customer customer = saveCustomerHelperMethod();
        assertEquals(1, customerRepository.findAllCustomers().size());
        customerRepository.deleteCustomerByEmail(customer.getEmail());
        assertEquals(0, customerRepository.findAllCustomers().size());
    }

    @Test
    void deleteCustomer() {
        Customer customer = saveCustomerHelperMethod();
        assertEquals(1, customerRepository.findAllCustomers().size());
        customerRepository.deleteCustomer(customer);
        assertEquals(0, customerRepository.findAllCustomers().size());
    }

    @Test
    void deleteAllCustomers() {
        Customer firstCustomer = new Customer();
        firstCustomer.setEmail("mrchibuzo@semicolon.africa");
        firstCustomer.setFullName("Uncle Chibob");
        firstCustomer.setPassword("semicolon01");
        customerRepository.saveCustomer(firstCustomer);

        Customer secondCustomer = new Customer();
        secondCustomer.setEmail("mrgabriel@semicolon.africa");
        secondCustomer.setFullName("Enum Master");
        secondCustomer.setPassword("semicolon02");
        customerRepository.saveCustomer(secondCustomer);

        customerRepository.deleteAllCustomers();
        assertEquals(0, customerRepository.findAllCustomers().size());
    }
}