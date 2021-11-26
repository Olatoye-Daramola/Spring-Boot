package africa.semicolon.jijiClone.utils;

import africa.semicolon.jijiClone.data.models.Lister;
import africa.semicolon.jijiClone.dtos.requests.RegisterListerRequest;
import africa.semicolon.jijiClone.dtos.responses.RegisterListerResponse;

import java.time.format.DateTimeFormatter;

public class ModelMapper {
    public static Lister map(RegisterListerRequest request) {
        Lister lister = new Lister();
        lister.setEmail(request.getEmail());
        lister.setFullName(request.getFullName());
        lister.setLocation(request.getLocation());
        lister.setPhoneNumber(request.getPhoneNumber());
        return lister;
    }

    public static RegisterListerResponse map(Lister lister) {
        RegisterListerResponse response = new RegisterListerResponse();
        response.setDbId(lister.getId());
        response.setDateAndTimeOfCreation(DateTimeFormatter
                .ofPattern("E, dd/MM/yyyy, hh:mm a")
                .format(lister.getDateRegistered()));
        return response;
    }
}
