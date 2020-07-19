package dao;

import org.sergio.domain.Question;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVQuestionDao implements QuestionDao {
    private Path csvFile;

    @Override
    public List<Question> readFile() {
        return null;
    }

    private Question parseString(String str) {
        if (str == null || str.isEmpty()) return new Question();

        return new Question();
    }

    private List<String> parsePattern(String text) {
        List<String> list = new ArrayList<>();
        if (text == null || text.isEmpty()) return list;
        Pattern pattern = Pattern.compile("(?:^|,)\\s*(?:(?:(?=\")\"([^\"].*?)\")|(?:(?!\")(.*?)))(?=,|$)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String tmp = matcher.group(1)==null? matcher.group(2):matcher.group(1);
            list.add(tmp);
        }
        return list;
    }
}

