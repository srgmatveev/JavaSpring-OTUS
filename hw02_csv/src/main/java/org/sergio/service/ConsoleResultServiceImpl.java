package org.sergio.service;

import org.sergio.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Map;


public class ConsoleResultServiceImpl implements ResultService {
    private PersonService personService;
    private PersonService simplePersonService;
    private AskQuestionService askQuestionService;

    public ConsoleResultServiceImpl(PersonService personService, PersonService simplePersonService, AskQuestionService askQuestionService) {
        this.personService = personService;
        this.simplePersonService = simplePersonService;
        this.askQuestionService = askQuestionService;
    }

    @Override
    public void writeHeader() {
        ConsoleHelper.writeMessage(personService.writeName());
        ConsoleHelper.writeMessage(personService.writeSurname());
    }

    @Override
    public void writeResult() {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Результат тестирования пользователя "
                + simplePersonService.writeName() + " " + simplePersonService.writeSurname());
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

        ConsoleHelper.writeMessage(String.format("Всего вопросов %d. Правильно: %d. Неверно: %d", count, right, wrong));
    }

    private void printQuestions() {
        Map<Question, Integer> map = askQuestionService.getResult();
        map.forEach((k, v) -> {
            if (k.getRightAnswer() == v) {
                ConsoleHelper.writeMessage(k.getQuestion() + ": Верно.");
            } else {
                ConsoleHelper.writeMessage(k.getQuestion() + ": Неверно.");
            }
        });
    }

}
