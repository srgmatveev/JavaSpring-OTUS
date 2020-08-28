package org.sergio.library.repositories;

import org.sergio.library.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "personRepo")
public interface PersonMongoRepository extends MongoRepository<Person, String> {

    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Person findByName(final String Name);

    @Query("{ 'surName' : {$regex: '^?0$', $options: 'i' }}")
    Person findBySurName(final String surName);

    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }, 'surName' : {$regex: '^?1$', $options: 'i' }}")
    Person findByNameAndSurName(final String Name, final String surName);

    default Person uniqSave(Person person) {
        Person person1 = findByNameAndSurName(person.getName(), person.getSurName());
        if (person1 == null) {
            person1 = this.save(person);
        }
        return person1;
    }
}
