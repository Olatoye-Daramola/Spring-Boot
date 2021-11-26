package africa.semicolon.cheetah.data.repositories;

import africa.semicolon.cheetah.data.models.TrackingData;
import africa.semicolon.cheetah.data.models.TrackingInformation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrackingInformationRepositoryImplTest {
    TrackingInformationRepository trackingInformationRepository;

    @BeforeEach
    void setUp() {
        trackingInformationRepository = new TrackingInformationRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        TrackingInformation trackingInformation = new TrackingInformation();
        trackingInformation.setPackageId(1234);
        TrackingData trackingData = new TrackingData("Package ready for dispatch");
        trackingData.setEvent("Package ready for dispatch");
        trackingInformation.getTrackingData().add(trackingData);
        TrackingInformation savedInfo = trackingInformationRepository.save(trackingInformation);

        assertEquals(savedInfo, trackingInformation);
        assertEquals(1, trackingInformationRepository.findAll().size());
    }

    private TrackingInformation savedTestHelper() {
        TrackingInformation trackingInformation = new TrackingInformation();
        trackingInformation.setPackageId(1234);
        TrackingData trackingData = new TrackingData("Package ready for dispatch");
        trackingData.setEvent("Package ready for dispatch");
        trackingInformation.getTrackingData().add(trackingData);
        return trackingInformationRepository.save(trackingInformation);
    }

    @Test
    void findAll() {
        savedTestHelper();
        assertEquals(1, trackingInformationRepository.findAll().size());
    }

    @Test
    void deleteAll() {
        savedTestHelper();
        assertEquals(1, trackingInformationRepository.findAll().size());
        trackingInformationRepository.deleteAll();
        assertEquals(0, trackingInformationRepository.findAll().size());
        assertTrue(trackingInformationRepository.findAll().isEmpty());
    }

    @Test
    void delete() {
        TrackingInformation savedTrackingInformation = savedTestHelper();
        assertEquals(1, trackingInformationRepository.findAll().size());
        trackingInformationRepository.delete(savedTrackingInformation.getPackageId());
        assertEquals(0, trackingInformationRepository.findAll().size());
    }

    @Test
    void testDelete() {
        TrackingInformation savedTrackingInformation = savedTestHelper();
        assertEquals(1, trackingInformationRepository.findAll().size());
        trackingInformationRepository.delete(savedTrackingInformation);
        assertEquals(0, trackingInformationRepository.findAll().size());
    }

    @Test
    void findByPackageId() {
        TrackingInformation savedTrackingInformation = savedTestHelper();
        Optional<TrackingInformation> optionalTrackingInformation = trackingInformationRepository.findByPackageId(savedTrackingInformation.getPackageId());
        assertTrue(optionalTrackingInformation.isPresent());
        assertEquals(savedTrackingInformation, optionalTrackingInformation.get());
    }
}