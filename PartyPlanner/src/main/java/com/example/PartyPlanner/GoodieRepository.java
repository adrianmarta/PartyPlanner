package com.example.PartyPlanner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodieRepository extends MongoRepository<Goodie, String>
{

}
