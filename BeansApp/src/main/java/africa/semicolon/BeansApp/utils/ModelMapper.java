package africa.semicolon.BeansApp.utils;

import africa.semicolon.BeansApp.data.models.Customer;
import africa.semicolon.BeansApp.dtos.requests.RegisterCustomerRequest;
import africa.semicolon.BeansApp.dtos.responses.RegisterCustomerResponse;

public class ModelMapper {
    public static Customer map(RegisterCustomerRequest registerCustomerRequest) {
        Customer customer = new Customer();
        customer.setEmail(registerCustomerRequest.getCustomerEmail());
        customer.setFullName(registerCustomerRequest.getCustomerFullName());
        customer.setPassword(registerCustomerRequest.getCustomerPassword());
        return customer;
    }

    public static RegisterCustomerResponse map(Customer customer) {
        RegisterCustomerResponse response = new RegisterCustomerResponse();
        response.setCustomerEmail(customer.getEmail());
        response.setCustomerPassword(customer.getPassword());
        return response;
    }
}
