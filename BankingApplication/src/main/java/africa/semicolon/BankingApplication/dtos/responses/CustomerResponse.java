package africa.semicolon.BankingApplication.dtos.responses;

import africa.semicolon.BankingApplication.data.models.Customer;
import lombok.Data;

@Data
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String bvn;
    private int numberOfAccounts;

    public CustomerResponse(Customer customer) {
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        bvn = customer.getBvn();
        numberOfAccounts = customer.getAccounts().size();
    }
}
