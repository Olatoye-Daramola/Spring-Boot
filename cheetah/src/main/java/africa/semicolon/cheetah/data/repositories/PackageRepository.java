package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.Package;

import java.util.List;

public interface PackageRepository {
    Package save(Package aPackage);
    List<Package> findAll();
    void delete(Package aPackage);
    void delete(Integer trackingNumber);
    Package findPackageByTrackingNumber(Integer trackingNumber);
    Package findPackageBySenderEmail(String emailAddress);
}
