package org.sergio;

import org.sergio.service.AskQuestionService;
import org.sergio.service.ResultService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Main {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) throws IOException {
        context = SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
        run();
    }

    private static void run() throws IOException {
        ResultService resultService = (ResultService) context.getBean("resultService");
        AskQuestionService questionService = (AskQuestionService) context.getBean("askQuestionService");
        if (resultService != null) {
            resultService.writeHeader();
            questionService.askQuestions();
            resultService.writeResult();
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
