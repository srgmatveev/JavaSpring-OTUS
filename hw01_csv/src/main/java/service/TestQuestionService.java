package service;

import org.sergio.domain.Question;

import java.util.List;

public interface TestQuestionService {
    List<Question> readAll();

    boolean rightAnswer(Question question);


}
