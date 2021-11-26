package africa.semicolon.cheetah.utils;

import africa.semicolon.cheetah.data.models.Package;
import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.data.models.TrackingData;
import africa.semicolon.cheetah.dtos.requests.AddPackageRequest;
import africa.semicolon.cheetah.dtos.requests.AddTrackingInfoRequest;
import africa.semicolon.cheetah.dtos.requests.RegisterSenderRequest;
import africa.semicolon.cheetah.dtos.responses.AddPackageResponse;
import africa.semicolon.cheetah.dtos.responses.AddTrackingInfoResponse;
import africa.semicolon.cheetah.dtos.responses.RegisterSenderResponse;

public class ModelMapper {
    public static Package map(AddPackageRequest addPackageRequest) {
        Package aPackage = new Package();
        aPackage.setName(addPackageRequest.getPackageDescription());
        aPackage.setDeliveryAddress(addPackageRequest.getDeliveryAddress());
        aPackage.setReceiverName(addPackageRequest.getReceiverName());
        aPackage.setReceiverPhone(addPackageRequest.getReceiverPhone());
        aPackage.setNetWeight(addPackageRequest.getPackageWeight());

        return aPackage;
    }

    public static AddPackageResponse map(Package savedPackage) {
        AddPackageResponse response = new AddPackageResponse();
        response.setPackageName(savedPackage.getName());
        response.setPackageWeight(savedPackage.getNetWeight());
        response.setReceiverName(savedPackage.getReceiverName());
        response.setReceiverPhone(savedPackage.getReceiverPhone());
        response.setTrackingNumber(savedPackage.getTrackingNumber());

        return response;
    }

    public static Sender map(RegisterSenderRequest registerSenderRequest) {
        Sender sender = new Sender();
        sender.setSenderName((registerSenderRequest.getSenderName()));
        sender.setSenderPhone(registerSenderRequest.getSenderPhone());
        sender.setEmailAddress(registerSenderRequest.getSenderEmail());

        return sender;
    }

    public static RegisterSenderResponse map(Sender sender) {
        RegisterSenderResponse response = new RegisterSenderResponse();
        response.setSenderEmail(sender.getEmailAddress());

        return response;
    }

    public static AddTrackingInfoResponse map(TrackingData trackingData, AddTrackingInfoRequest addTrackingInfoRequest) {
        AddTrackingInfoResponse addTrackingInfoResponse = new AddTrackingInfoResponse();
        addTrackingInfoResponse.setDateTimeOfEvent(trackingData.getDateTimeOfEvent());
        addTrackingInfoResponse.setEvent(trackingData.getEvent());
        addTrackingInfoResponse.setPackageId(addTrackingInfoRequest.getPackageId());
        return addTrackingInfoResponse;
    }
}
