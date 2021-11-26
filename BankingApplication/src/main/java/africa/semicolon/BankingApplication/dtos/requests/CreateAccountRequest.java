package africa.semicolon.BankingApplication.dtos.requests;

import africa.semicolon.BankingApplication.data.models.AccountType;
import lombok.Data;

@Data
public class CreateAccountRequest {
    private String bankId;
    private AccountType accountType;
    private String firstName;
    private String lastName;
}
