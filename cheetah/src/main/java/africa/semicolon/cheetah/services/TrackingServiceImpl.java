package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.data.models.TrackingData;
import africa.semicolon.cheetah.data.models.TrackingInformation;
import africa.semicolon.cheetah.data.repositories.TrackingInformationRepository;
import africa.semicolon.cheetah.data.repositories.TrackingInformationRepositoryImpl;
import africa.semicolon.cheetah.dtos.requests.AddTrackingInfoRequest;
import africa.semicolon.cheetah.dtos.responses.AddTrackingInfoResponse;
import africa.semicolon.cheetah.exceptions.InvalidPackageException;
import africa.semicolon.cheetah.utils.ModelMapper;

import java.util.Optional;

public class TrackingServiceImpl implements TrackingService {
    private PackageService packageService = new PackageServiceImpl();
    private TrackingInformationRepository trackingRepo = new TrackingInformationRepositoryImpl();

    @Override
    public AddTrackingInfoResponse updateTrackingInfo(AddTrackingInfoRequest addTrackingInfoRequest) {
//        verify that packageId is correct
        var aPackage = packageService.findMyPackageWithMy(addTrackingInfoRequest.getPackageId());
        if(aPackage == null) throw new InvalidPackageException("Package Id is invalid");

        TrackingData trackingData = new TrackingData(addTrackingInfoRequest.getEvent());
//        find previous tracking info
        Optional<TrackingInformation> optionalInfo = trackingRepo.findByPackageId(addTrackingInfoRequest.getPackageId());

        TrackingInformation response = null;
//            if exist add new event and save
        if(optionalInfo.isPresent()) {
            optionalInfo.get().getTrackingData().add(trackingData);
            response = trackingRepo.save(optionalInfo.get());
        }
//            else create new tracking info, add new event
        else {
            TrackingInformation trackingInformation = new TrackingInformation();
            trackingInformation.setPackageId(addTrackingInfoRequest.getPackageId());
            trackingInformation.getTrackingData().add(trackingData);
            response = trackingRepo.save(trackingInformation);
        }
//        convert saved tracking info to response dto
        return ModelMapper.map(trackingData, addTrackingInfoRequest);
    }

    @Override
    public TrackingInformation trackPackage(Integer packageId) {
        return trackingRepo.findByPackageId(packageId).get();
    }
}
