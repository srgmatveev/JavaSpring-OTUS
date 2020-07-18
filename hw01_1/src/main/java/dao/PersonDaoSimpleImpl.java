package dao;

import domain.Person;

public class PersonDaoSimpleImpl implements PersonDao {
    @Override
    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
