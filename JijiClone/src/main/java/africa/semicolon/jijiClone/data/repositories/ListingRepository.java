package africa.semicolon.jijiClone.data.repositories;

import africa.semicolon.jijiClone.data.models.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends MongoRepository<Listing, String> {
}
