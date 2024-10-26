package com.example.PartyPlanner;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyRepository extends MongoRepository<Party, String> {
    Optional<Party> findPartyByObjectId(String objectId);

    Optional<Party> findById(String partyId);
}
