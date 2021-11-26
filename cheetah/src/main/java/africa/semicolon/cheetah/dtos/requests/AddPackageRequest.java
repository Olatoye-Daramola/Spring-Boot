package africa.semicolon.cheetah.dtos.requests;

import lombok.Data;

@Data
public class AddPackageRequest {
    private String senderEmail;
    private String receiverName;
    private String receiverPhone;
    private String deliveryAddress;
    private double packageWeight;
    private String packageDescription;
}
