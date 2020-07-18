package service;

import dao.PersonDao;
import domain.Person;

public class PersonServiceImpl implements PersonService {
    private PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }

}
