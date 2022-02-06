package africa.semicolon.employeemanager.data.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;

    public String generateEmail() {
        return firstName.toLowerCase().charAt(0) + "." + lastName.toLowerCase() + "@email.com";
    }
}
