package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.Package;
import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.services.SenderService;
import africa.semicolon.cheetah.services.SenderServiceImpl;

import java.util.*;

public class PackageRepositoryImpl implements PackageRepository {
    private final Map<Integer, Package> database = new HashMap<>();
    private final SenderService senderService = new SenderServiceImpl();

    @Override
    public Package save(Package aPackage) {
        Integer trackingNumber = null;
        if (aPackage.getTrackingNumber() == null) {
            trackingNumber = database.size() + 1;
            aPackage.setTrackingNumber(trackingNumber);
        }
        trackingNumber = aPackage.getTrackingNumber();
        database.put(trackingNumber, aPackage);
        return database.get(trackingNumber);
    }

    @Override
    public List<Package> findAll() {
        List<Package> all = new ArrayList<>();
        Set<Integer> keys = database.keySet();
        for(Integer key : keys) {
            all.add(database.get(key));
        }
        return all;
    }

    @Override
    public void delete(Package aPackage) {
        database.remove(aPackage.getTrackingNumber());
    }

    @Override
    public void delete(Integer trackingNumber) {
        database.remove(trackingNumber);
    }

    @Override
    public Package findPackageByTrackingNumber(Integer trackingNumber) {
        return database.get(trackingNumber);
    }

    @Override
    public Package findPackageBySenderEmail(String emailAddress) {
        Optional<Sender> sender = senderService.findSenderByEmail(emailAddress);
        Set<Integer> keys = database.keySet();
        for(Integer key : keys) {
            if(database.get(key).getSenderEmail().equals(sender.get().getEmailAddress()))
                return database.get(key);
        }
        return null;
    }
}
