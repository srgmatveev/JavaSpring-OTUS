package dao;

import org.sergio.domain.Question;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVQuestionDao implements QuestionDao {
    private Path csvFile;

    public CSVQuestionDao() {
    }

    public CSVQuestionDao(Path path) {

        if (Files.exists(path) && Files.isRegularFile(path))
            this.csvFile = path;
        else throw new IllegalArgumentException("wrong CSV file path " + path);

    }


    @Override
    public List<Question> readAll() {
        List<Question> questions = new ArrayList<>();
        InputStream is = null;
        try {
            is = Files.newInputStream(csvFile);
        } catch (IOException e) {
            e.printStackTrace();
            return questions;
        }
        try (
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
        ) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Question question = this.parseString(line);
                if (!question.isEmpty())
                    questions.add(question);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
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

