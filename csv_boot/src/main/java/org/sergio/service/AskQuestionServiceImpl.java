package org.sergio.service;

import org.sergio.domain.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("askQuestionService")
public class AskQuestionServiceImpl implements AskQuestionService {
    private TestQuestionService testQuestionService;
    private Map<Question, Integer> map;
    private MessageSource ms;

    public AskQuestionServiceImpl(MessageSource ms, TestQuestionService testQuestionService) {
        this.ms = ms;
        this.testQuestionService = testQuestionService;
        this.map = new HashMap<>();
    }

    @Override
    public void askQuestions() throws IOException {
        List<Question> questions = testQuestionService.readAll();
        if (questions == null) return;
        ConsoleHelper.writeMessage(ms.getMessage("ask.questions", null, Locale.getDefault()));
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            workWithQuestion(question);
        }

        return;
    }

    private void workWithQuestion(Question question) throws IOException {
        ConsoleHelper.writeMessage(question.getQuestion());
        ConsoleHelper.writeMessage(ms.getMessage("ask.variants", null, Locale.getDefault()));
        ConsoleHelper.writeMessage(question.getAnswers().toString());
        ConsoleHelper.writeMessage(ms.getMessage("ask.select.variant", null, Locale.getDefault()));
        while (true)
            try {
                int answer = Integer.parseInt(ConsoleHelper.readMessage());
                if (answer < 1) throw new NumberFormatException();
                map.put(question, answer);
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(ms.getMessage("ask.wrong.number.select", null, Locale.getDefault()));
            }
    }

    @Override
    public Map<Question, Integer> getResult() {
        return map;
    }
}
