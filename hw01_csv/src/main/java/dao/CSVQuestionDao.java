package dao;

import org.sergio.domain.Question;

import java.nio.file.Path;
import java.text.NumberFormat;
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
        List<String> list = parsePattern(str);
        if (list.size() < 3) return new Question();
        String questionStr = list.get(0);
        int answer = 0;
        try {
            answer = Integer.parseInt(list.get(list.size() - 1));
            if (answer == 0 || answer > list.size() - 2)
                throw new NumberFormatException("wrong answer");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Question();
        }
        list.remove(0);
        list.remove(list.size() - 1);
        Question question = new Question(questionStr, list, answer);
        return question;
    }

    private List<String> parsePattern(String text) {
        List<String> list = new ArrayList<>();
        if (text == null || text.isEmpty()) return list;
        Pattern pattern = Pattern.compile("(?:^|,)\\s*(?:(?:(?=\")\"([^\"].*?)\")|(?:(?!\")(.*?)))(?=,|$)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String tmp = matcher.group(1) == null ? matcher.group(2) : matcher.group(1);
            list.add(tmp);
        }
        return list;
    }
}

