package africa.semicolon.cheetah.data.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class TrackingData {
    private LocalDateTime dateTimeOfEvent = LocalDateTime.now();
    @NonNull
    private String event;
}
