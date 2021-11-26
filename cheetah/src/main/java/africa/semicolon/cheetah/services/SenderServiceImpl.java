package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.data.repositories.SenderRepository;
import africa.semicolon.cheetah.data.repositories.SenderRepositoryImpl;
import africa.semicolon.cheetah.dtos.requests.RegisterSenderRequest;
import africa.semicolon.cheetah.dtos.responses.RegisterSenderResponse;
import africa.semicolon.cheetah.exceptions.DuplicateUserException;
import africa.semicolon.cheetah.exceptions.UserNotFoundException;
import africa.semicolon.cheetah.utils.ModelMapper;

import java.util.List;
import java.util.Optional;

public class SenderServiceImpl implements SenderService {
    private static final SenderRepository senderRepository = new SenderRepositoryImpl();

    @Override
    public RegisterSenderResponse registerSenderResponse(RegisterSenderRequest registerSenderRequest) {
        Optional<Sender> senderInDatabase = senderRepository.findSenderByEmail(registerSenderRequest.getSenderEmail());
        if (senderInDatabase.isPresent()) throw new DuplicateUserException(registerSenderRequest.getSenderEmail() + " already exists");
        Sender sender = ModelMapper.map(registerSenderRequest);
        Sender savedSender = senderRepository.save(sender);
        return ModelMapper.map(savedSender);
    }

    @Override
    public List<Sender> getSenders() {
        return senderRepository.findAll();
    }

    @Override
    public Optional<Sender> findSenderByEmail(String email) {
        if (!senderRepository.findSenderByEmail(email).isPresent()) throw new UserNotFoundException("Sender not found");
        return senderRepository.findSenderByEmail(email);
    }

    @Override
    public void deleteAllSenders() {
        senderRepository.deleteAll();
    }
}
