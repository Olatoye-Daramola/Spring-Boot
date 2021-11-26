package africa.semicolon.BeansApp.controllers;

import africa.semicolon.BeansApp.data.models.Customer;
import africa.semicolon.BeansApp.dtos.requests.RegisterCustomerRequest;
import africa.semicolon.BeansApp.dtos.responses.RegisterCustomerResponse;
import africa.semicolon.BeansApp.exceptions.CustomerNotFoundException;
import africa.semicolon.BeansApp.exceptions.DuplicateCustomerException;
import africa.semicolon.BeansApp.services.CustomerService;
import africa.semicolon.BeansApp.services.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    private final CustomerService customerService = new CustomerServiceImpl();

    @PostMapping("/api/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
        try {
            RegisterCustomerResponse response = customerService.registerCustomer(registerCustomerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        catch (DuplicateCustomerException error ) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/getCustomer/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email) {
        try {
            Optional<Customer> response = customerService.getCustomerByEmail(email);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        catch(CustomerNotFoundException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
