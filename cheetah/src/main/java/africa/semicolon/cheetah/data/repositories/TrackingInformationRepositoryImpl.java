package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.TrackingInformation;

import java.util.*;

public class TrackingInformationRepositoryImpl implements TrackingInformationRepository {
    private final Map<Integer, TrackingInformation> database = new HashMap<>();

    @Override
    public TrackingInformation save(TrackingInformation trackingInformation) {
        database.put(trackingInformation.getPackageId(), trackingInformation);
        return trackingInformation;
    }

    @Override
    public List<TrackingInformation> findAll() {
        List<TrackingInformation> all = new ArrayList<>();
        Set<Integer> keys = database.keySet();
        for (Integer key : keys) {
            all.add(database.get(key));
        }
        return all;
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public void delete(TrackingInformation trackingInformation) {
        delete(trackingInformation.getPackageId());
    }

    @Override
    public void delete(Integer packageId) {
        database.remove(packageId);

    }

    @Override
    public Optional<TrackingInformation> findByPackageId(Integer packageId) {
        if(database.containsKey(packageId)) return Optional.of(database.get(packageId));
        return Optional.empty();
    }
}
