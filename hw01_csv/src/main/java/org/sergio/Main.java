package org.sergio;

import org.sergio.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ConsoleHelper;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");

        ConsoleHelper.writeMessage("Введите имя");
        String name = ConsoleHelper.readMessage();
        ConsoleHelper.writeMessage("Введите фамилию");
        String surName = ConsoleHelper.readMessage();
        Person person = (Person) context.getBean("person",name,surName);
        System.out.println(person.getName());
        System.out.println(person.getSurName());
    }
}
