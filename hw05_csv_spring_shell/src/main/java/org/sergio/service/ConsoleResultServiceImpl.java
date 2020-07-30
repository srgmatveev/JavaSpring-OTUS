package org.sergio.service;

import org.sergio.domain.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service("resultService")
public class ConsoleResultServiceImpl implements ResultService {
    private final PersonService personService;
    private final AskQuestionService askQuestionService;
    private final MessageSource ms;

    public ConsoleResultServiceImpl(PersonService personService, AskQuestionService askQuestionService, MessageSource ms) {
        this.personService = personService;
        this.askQuestionService = askQuestionService;
        this.ms = ms;
    }

    @Override
    public void writeHeader() {
        ConsoleHelper.writeMessage(personService.writeName());
        ConsoleHelper.writeMessage(personService.writeSurname());
    }

    @Override
    public void writeResult() {
        ConsoleHelper.writeMessage("");
      String format = ms.getMessage("result.user.pattern", null, Locale.getDefault());
      String resUserFullName = String.format(format,personService.writeFullName());
        ConsoleHelper.writeMessage(resUserFullName);
        printQuestions();
        printTotal();
    }

    private void printTotal() {
        Map<Question, Integer> map = askQuestionService.getResult();
        int count = 0;
        int wrong = 0, right = 0;
        for (Map.Entry<Question, Integer> entry : map.entrySet()) {
            ++count;
            if (entry.getKey().getRightAnswer() == entry.getValue())
                ++right;
            else ++wrong;
        }

       Integer[] arr= { count, right, wrong};
        ConsoleHelper.writeMessage(ms.getMessage("result.final.pattern", arr, Locale.getDefault()));
    }

    private void printQuestions() {
        Map<Question, Integer> map = askQuestionService.getResult();
        map.forEach((k, v) -> {
            if (k.getRightAnswer() == v) {
                ConsoleHelper.writeMessage(k.getQuestion() + ms.getMessage("result.final.right", null,Locale.getDefault()));
            } else {
                ConsoleHelper.writeMessage(k.getQuestion() + ms.getMessage("result.final.wrong", null,Locale.getDefault()));
            }
        });
    }

}
