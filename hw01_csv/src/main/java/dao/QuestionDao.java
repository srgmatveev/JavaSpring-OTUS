package dao;

import org.sergio.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> readFile();
}
