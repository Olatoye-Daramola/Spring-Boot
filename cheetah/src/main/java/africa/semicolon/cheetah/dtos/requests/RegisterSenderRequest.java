package africa.semicolon.cheetah.dtos.requests;

import lombok.Data;

@Data
public class RegisterSenderRequest {
    private String senderEmail;
    private String senderName;
    private String senderPhone;
}
