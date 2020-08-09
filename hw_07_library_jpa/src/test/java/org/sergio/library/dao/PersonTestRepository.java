package org.sergio.library.dao;

import org.sergio.library.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "personTestRepo")
public interface PersonTestRepository extends JpaRepository<Person, Long> {
}
