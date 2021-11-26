package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.Sender;

import java.util.List;
import java.util.Optional;

public interface SenderRepository {
    Sender save(Sender sender);
    Optional<Sender> findSenderByEmail(String senderEmail);
    List<Sender> findAll();
    void deleteBySenderEmail(String senderEmail);
    void deleteBySender(Sender sender);
    void deleteAll();
}
