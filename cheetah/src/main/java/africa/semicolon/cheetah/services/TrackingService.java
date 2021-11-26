package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.data.models.TrackingInformation;
import africa.semicolon.cheetah.dtos.requests.AddTrackingInfoRequest;
import africa.semicolon.cheetah.dtos.responses.AddTrackingInfoResponse;

public interface TrackingService {
    AddTrackingInfoResponse updateTrackingInfo(AddTrackingInfoRequest addTrackingRequest);

    TrackingInformation trackPackage(Integer packageId);
}
