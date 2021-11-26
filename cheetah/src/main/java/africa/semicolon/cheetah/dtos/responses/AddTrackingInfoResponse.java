package africa.semicolon.cheetah.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTrackingInfoResponse {
    private Integer packageId;
    private LocalDateTime dateTimeOfEvent;
    private String event;
}
