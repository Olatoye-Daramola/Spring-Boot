package africa.semicolon.cheetah.data.models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackingInformation {
    private Integer packageId;
    private final List<TrackingData> trackingData = new ArrayList<>();
}
