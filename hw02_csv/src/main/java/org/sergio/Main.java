package org.sergio;

import org.sergio.domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.sergio.service.AskQuestionService;
import org.sergio.service.ConsoleHelper;
import org.sergio.service.PersonService;
import org.sergio.service.ResultService;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "org.sergio.config")
public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("ru", "RU"));
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(MethodHandles.lookup().lookupClass());
        ctx.refresh();
        run(ctx);
    }

    private static void run(ApplicationContext context) throws IOException {
        String name;
        String surName;
        if (DEBUG) {
            name = "Sergey";
            surName = "Matveev";
        } else {
            ConsoleHelper.writeMessage("Введите имя");
            name = ConsoleHelper.readMessage();
            ConsoleHelper.writeMessage("Введите фамилию");
            surName = ConsoleHelper.readMessage();
        }
//          Person person = (Person) context.getBean("person", name, surName);
//          PersonService personService = (PersonService) context.getBean("personService", person);
//          PersonService simplePersonService = (PersonService) context.getBean("simplePersonService", person);
//        AskQuestionService askQuestionService = (AskQuestionService) context.getBean("askQuestion");
//        ResultService resultService = (ResultService) context.getBean("consoleResultService", personService, simplePersonService, askQuestionService);
//        resultService.writeHeader();
//        askQuestionService.askQuestions();
//        resultService.writeResult();
    }

}
