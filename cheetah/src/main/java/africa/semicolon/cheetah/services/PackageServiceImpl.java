package africa.semicolon.cheetah.services;

import africa.semicolon.cheetah.data.models.Package;
import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.data.repositories.PackageRepository;
import africa.semicolon.cheetah.data.repositories.PackageRepositoryImpl;
import africa.semicolon.cheetah.dtos.requests.AddPackageRequest;
import africa.semicolon.cheetah.dtos.responses.AddPackageResponse;
import africa.semicolon.cheetah.exceptions.UserNotFoundException;
import africa.semicolon.cheetah.utils.ModelMapper;

import java.util.Optional;


public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository = new PackageRepositoryImpl();
    private final SenderService senderService = new SenderServiceImpl();

    @Override
    public AddPackageResponse addPackage(AddPackageRequest addPackageRequest) {
        Optional<Sender> optionalSender= senderService.findSenderByEmail(addPackageRequest.getSenderEmail());
        if(optionalSender.isEmpty()) throw new UserNotFoundException("Sender not found");
//        convert addPackage request to a package
        Package aPackage = ModelMapper.map(addPackageRequest);
//        save package
        Package savedPackage = packageRepository.save(aPackage);
//        convert saved package to addPackage response
        AddPackageResponse addPackageResponse = ModelMapper.map(savedPackage);
//        return converted response
        return addPackageResponse;
    }

    @Override
    public Package findMyPackageWithMy(Integer trackingNumber) {
        return packageRepository.findPackageByTrackingNumber(trackingNumber);
    }
}
