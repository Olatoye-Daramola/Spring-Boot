package africa.semicolon.BeansApp.dtos.requests;

import lombok.Data;

@Data
public class RegisterCustomerRequest {
    private String customerEmail;
    private String customerFullName;
    private String customerPassword;
}
