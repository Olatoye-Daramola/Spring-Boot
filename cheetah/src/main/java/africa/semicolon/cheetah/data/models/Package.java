package africa.semicolon.cheetah.data.models;

import lombok.Data;

@Data
public class Package {
    private Integer trackingNumber;
    private String name;
    private String senderEmail;
    private String receiverName;
    private String receiverPhone;
    private String deliveryAddress;
    private double netWeight;
}