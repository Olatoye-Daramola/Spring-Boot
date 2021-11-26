package africa.semicolon.jijiClone.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterListerRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String location;
}
