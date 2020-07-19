package org.sergio;

import org.sergio.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        Person person = (Person) context.getBean("person","Sergey","Matveev");
        System.out.println(person.getName());
        System.out.println(person.getSurName());
    }
}
