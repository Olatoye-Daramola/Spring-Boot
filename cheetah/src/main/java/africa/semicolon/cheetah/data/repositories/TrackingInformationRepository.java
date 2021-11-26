package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.TrackingInformation;

import java.util.List;
import java.util.Optional;

public interface TrackingInformationRepository {
    TrackingInformation save(TrackingInformation trackingInformation);
    List<TrackingInformation> findAll();
    void deleteAll();
    void delete(TrackingInformation trackingInformation);
    void delete(Integer packageId);
    Optional<TrackingInformation> findByPackageId(Integer packageId);
}
