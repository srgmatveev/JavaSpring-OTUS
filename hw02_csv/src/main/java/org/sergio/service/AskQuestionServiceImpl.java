package org.sergio.service;

import org.sergio.domain.Question;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AskQuestionServiceImpl implements AskQuestionService {
    private TestQuestionService testQuestionService;
    private Map<Question, Integer> map;

    public AskQuestionServiceImpl(TestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
        this.map = new HashMap<>();
    }

    @Override
    public void askQuestions() throws IOException {
        List<Question> questions = testQuestionService.readAll();
        if (questions == null) return;
        ConsoleHelper.writeMessage("Ответьте на вопросы");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            workWithQuestion(question);
        }

        return;
    }

    private void workWithQuestion(Question question) throws IOException {
        ConsoleHelper.writeMessage(question.getQuestion());
        ConsoleHelper.writeMessage("Варианты");
        ConsoleHelper.writeMessage(question.getAnswers().toString());
        ConsoleHelper.writeMessage("Выберите вариант (нумерация с 1)");
        while (true)
            try {
                int answer = Integer.parseInt(ConsoleHelper.readMessage());
                if (answer < 1) throw new NumberFormatException();
                map.put(question, answer);
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Недопустимый номер ответа");
            }
    }

    @Override
    public Map<Question, Integer> getResult() {
        return map;
    }
}
