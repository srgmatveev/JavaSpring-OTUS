package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        PersonDao dao = context.getBean(PersonDao.class);

        System.out.println("All count " + dao.count());

        Person person = new Person(2, "Вася");
        dao.insert(person);

        System.out.println("All count " + dao.count());

        person = dao.getById(1);
        System.out.println(person.toString());

        System.out.println(dao.getAll());

        person = dao.getByID(1);
        System.out.println(person.toString());
        dao.deleteByID(2);
        System.out.println(dao.getAll());
        dao.deleteByID(2);
        System.out.println(dao.getAll());
        // Console.main(args);
    }
}
