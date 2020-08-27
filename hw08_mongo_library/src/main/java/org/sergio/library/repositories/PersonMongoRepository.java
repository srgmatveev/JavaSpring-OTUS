package org.sergio.library.repositories;

import org.sergio.library.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "personRepo")
public interface PersonMongoRepository extends MongoRepository<Person, String> {
}
