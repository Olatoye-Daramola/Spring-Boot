package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.exceptions.UserNotFoundException;

import java.util.*;

public class SenderRepositoryImpl implements SenderRepository {
    Map<String, Sender> database = new HashMap<String, Sender>();

    @Override
    public Sender save(Sender sender) {
        database.put(sender.getEmailAddress(), sender);
        return sender;
    }

    @Override
    public Optional<Sender> findSenderByEmail(String senderEmail) {
        if(!(database.containsKey(senderEmail))) throw new UserNotFoundException("User does not exist");
        return Optional.of(database.get(senderEmail));
    }

    @Override
    public void deleteBySenderEmail(String senderEmail) {
        database.remove(senderEmail);
    }

    @Override
    public void deleteBySender(Sender sender) {
        deleteBySenderEmail(sender.getEmailAddress());
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public List<Sender> findAll() {
        List<Sender> senderList = new ArrayList<>();
        Set<String> keys = database.keySet();
        for (String key : keys) {
            senderList.add(database.get(key));
        }
        return senderList;
    }
}
