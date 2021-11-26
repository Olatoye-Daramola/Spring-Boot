package africa.semicolon.BeansApp.dtos.responses;

import lombok.Data;

@Data
public class RegisterCustomerResponse {
    private String customerEmail;
    private String customerPassword;
}
