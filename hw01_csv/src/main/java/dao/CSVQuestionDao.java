package dao;

import org.sergio.domain.Question;

import java.nio.file.Path;
import java.util.List;

public class CSVQuestionDao implements QuestionDao{
    private Path csvFile;

    @Override
    public List<Question> readFile() {
        return null;
    }
}
