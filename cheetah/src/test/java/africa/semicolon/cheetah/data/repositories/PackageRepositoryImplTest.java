package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.Package;
import africa.semicolon.cheetah.data.models.Sender;
import africa.semicolon.cheetah.dtos.requests.RegisterSenderRequest;
import africa.semicolon.cheetah.dtos.responses.RegisterSenderResponse;
import africa.semicolon.cheetah.services.SenderService;
import africa.semicolon.cheetah.services.SenderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageRepositoryImplTest {
    private PackageRepository packageRepository;

    @BeforeEach
    void setUp() {
        packageRepository = new PackageRepositoryImpl();
    }

    @AfterEach
    void tearDown() {

    }

    public Package saveNewPackage() {
        Package aPackage = new Package();
        aPackage.setName("Sleeping Mat");
        aPackage.setSenderEmail("Jerry@email.com");
        aPackage.setReceiverName("Dammy");
        aPackage.setReceiverPhone("0123456789");
        aPackage.setDeliveryAddress("312, Herbert Macaulay Way, Sabo-Yaba, Lagos");
        aPackage.setNetWeight(23.5);

        return packageRepository.save(aPackage);
    }

    @Test
    void saveNewPackageTest() {
        Package savedPackage = saveNewPackage();
        assertEquals(1, savedPackage.getTrackingNumber());
    }

    @Test
    void updatePackageTest() {
        Package savedPackage = saveNewPackage();

        savedPackage.setName("Sniper");
        Package updatedPackage = packageRepository.save(savedPackage);
        assertEquals(1, updatedPackage.getTrackingNumber());
    }

    @Test
    void findAll() {
        Package savedPackage = saveNewPackage();

        assertEquals(1, packageRepository.findAll().size());
    }

    @Test
    void delete() {
        Package savedPackage = saveNewPackage();

        assertEquals(1, packageRepository.findAll().size());

        packageRepository.delete(savedPackage);
        assertEquals(0, packageRepository.findAll().size());
    }

    @Test
    void deleteByTrackingNumber() {
        Package savedPackage = saveNewPackage();

        assertEquals(1, packageRepository.findAll().size());

        packageRepository.delete(savedPackage);
        assertEquals(0, packageRepository.findAll().size());
    }

    @Test
    void findPackageByTrackingNumber() {
        Package savedPackage = saveNewPackage();

        assertEquals(savedPackage, packageRepository.findPackageByTrackingNumber(1));
    }

    @Test
    void findPackageBySenderEmailAddress() {
        Package savedPackage = saveNewPackage();
        SenderService senderService = new SenderServiceImpl();
        Sender sender = new Sender();

        RegisterSenderRequest registerSenderRequest = new RegisterSenderRequest();
        registerSenderRequest.setSenderEmail(savedPackage.getSenderEmail());

        senderService.registerSenderResponse(registerSenderRequest);

//        assertEquals(savedPackage.getSenderEmail(), senderService.findSenderByEmail();
    }
}