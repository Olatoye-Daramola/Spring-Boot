package africa.semicolon.jijiClone.data.repositories;

import africa.semicolon.jijiClone.data.models.Condition;
import africa.semicolon.jijiClone.data.models.Listing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class ListingRepositoryTest {

    @Autowired
    private ListingRepository listingRepository;

    @Test
    public void saveListingTest() {
        Listing listing = new Listing();
        listing.setCondition(Condition.USED);
        listing.setName("G-String");
        listing.setShortDescription("London-used G-String");
        listing.setLongDescription("Tokunbo G-String");

        Listing savedListing = listingRepository.save(listing);
        assertEquals("Tokunbo G-String", savedListing.getLongDescription());
    }
}