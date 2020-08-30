package org.sergio.library.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonMongoRepositoryTest {

    private final PersonMongoRepository personMongoRepository;

    PersonMongoRepositoryTest(@Qualifier("personRepo") PersonMongoRepository personMongoRepository) {
        this.personMongoRepository = personMongoRepository;
    }


    @BeforeEach
    void setUp() {
        personMongoRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {

        //  personMongoRepository.deleteAll();
    }

    @Test
    @Transactional
    void insertPerson() {
        Person person = new Person("Sergio", "Matveev");
        personMongoRepository.save(person);
        assertEquals(personMongoRepository.count(), 1);
        Person person1 = new Person("Sergio1", "Matveev");
        personMongoRepository.save(person1);
        assertEquals(personMongoRepository.count(), 2);
    }

    @Test
    @Transactional
    void findByName() {
        Person person = new Person("Sergio", "Matveev");
        personMongoRepository.save(person);
        Person person1 = personMongoRepository.findByName("sergio");
        assertNotNull(person1.getId());
        assertEquals(person1.getName(), "Sergio");
        person1 = personMongoRepository.findByName("serg");
        assertNull(person1);
    }

    @Test
    @Transactional
    void findBySurName() {
        Person person = new Person("Sergio", "Matveev");
        personMongoRepository.save(person);
        Person person1 = personMongoRepository.findBySurName("Matveev");
        assertNotNull(person1.getId());
        assertEquals(person1.getSurName(), "Matveev");
        person1 = personMongoRepository.findBySurName("matve");
        assertNull(person1);
    }

    @Test
    @Transactional
    void findByNameAndSurName() {
        Person person = new Person("Sergio", "Matveev");
        personMongoRepository.save(person);

        List<Person> persons = personMongoRepository.findByNameAndSurName("Sergio", "Matveev");
        Person person1 = persons.get(0);
        assertNotNull(person1.getId());
        assertEquals(person1.getName(), "Sergio");
        assertEquals(person1.getSurName(), "Matveev");
        persons = personMongoRepository.findByNameAndSurName("Sergi", "Matveev");
        assertTrue(persons.isEmpty());
        persons = personMongoRepository.findByNameAndSurName("Sergio", "Matvee");
        assertTrue(persons.isEmpty());
    }

    @Test
    @Transactional
    void uniqSave() {
        Person person = new Person("Sergio", "Matveev");
        personMongoRepository.uniqSave(person);
        Person person1 = new Person("Sergio", "Matveev");
        List<Person> list = personMongoRepository.findAll(Example.of(person1));
        assertEquals(list.size(), 1);
        Person person2 = new Person("Sergio", "Matveev");
        personMongoRepository.uniqSave(person2);
        list = personMongoRepository.findAll(Example.of(person1));
        assertEquals(list.size(), 1);

    }
}