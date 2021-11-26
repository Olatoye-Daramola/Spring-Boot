package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.dtos.requests.RegisterSenderRequest;
import africa.semicolon.cheetah.dtos.responses.RegisterSenderResponse;

import java.util.List;
import java.util.Optional;

public interface SenderService {
    RegisterSenderResponse registerSenderResponse(RegisterSenderRequest senderRequest);

    List<Sender> getSenders();

    void deleteAllSenders();

    Optional<Sender> findSenderByEmail(String email);
}
