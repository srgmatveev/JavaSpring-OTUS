package service;

import dao.QuestionDao;
import org.sergio.domain.Question;

import java.util.List;

public class TestQuestionServiceImpl implements TestQuestionService{
    QuestionDao dao;

    public TestQuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> readAll() {
        return null;
    }

    @Override
    public boolean rightAnswer(Question question) {
        return false;
    }
}
