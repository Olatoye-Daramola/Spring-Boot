package africa.semicolon.cheetah.controllers;

import africa.semicolon.cheetah.data.models.TrackingInformation;
import africa.semicolon.cheetah.dtos.requests.AddTrackingInfoRequest;
import africa.semicolon.cheetah.dtos.responses.AddTrackingInfoResponse;
import africa.semicolon.cheetah.services.TrackingService;
import africa.semicolon.cheetah.services.TrackingServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackingController {
    private final TrackingService trackingService = new TrackingServiceImpl();

    @PatchMapping("/api/trackingInfo")
    public AddTrackingInfoResponse addTrackingInfo(@RequestBody AddTrackingInfoRequest addTrackingInfoRequest) {
        return trackingService.updateTrackingInfo(addTrackingInfoRequest);
    }

    @GetMapping("/api/trackingInfo/{trackingId}")
    public TrackingInformation getTrackingInfo(@PathVariable Integer trackingId) {
        return trackingService.trackPackage(trackingId);
    }
}
