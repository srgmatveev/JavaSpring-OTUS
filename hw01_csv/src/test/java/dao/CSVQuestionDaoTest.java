package dao;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sergio.domain.Question;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.List;

import static org.testng.Assert.*;

public class CSVQuestionDaoTest extends Assert {
    private Method parseString;
    private Method parsePattern;
    private Method readAll;
    private CSVQuestionDao csvQuestionDao;


    Path create(Path path, String fileName) {
        Path filePath = path.resolve(fileName);
        try {
            return Files.createFile(filePath);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    void update(Path path, String[] newContent) {
        try {
            for (int i = 0; i < newContent.length; i++) {
                Files.write(path, newContent[i].getBytes(), StandardOpenOption.APPEND);
            }

        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @BeforeMethod
    public void setUp() throws NoSuchMethodException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        String fileName = "testFile.csv";
        Path pathToStore = fileSystem.getPath("");
        Path path = create(pathToStore, fileName);
        csvQuestionDao = new CSVQuestionDao(path);
        String[] fileCSV = {
                "\"Имя собаки Герасима\",\"Муму\",\"Жужу\",\"Белка\",\"Стрелка\", \"Лайма, Фигайма\",1\n",
                "\"Имя создателя Linux\",\"Adolf\",\"Linus\",\"Pingus\",\"Richard\",\"Batman\",2\n",
        };
        update(path, fileCSV);
        parsePattern = csvQuestionDao.getClass().getDeclaredMethod("parsePattern", String.class);
        parsePattern.setAccessible(true);
        parseString = csvQuestionDao.getClass().getDeclaredMethod("parseString", String.class);
        parseString.setAccessible(true);
        readAll = csvQuestionDao.getClass().getDeclaredMethod("readAll");
        parseString.setAccessible(true);

    }

    @DataProvider
    public Object[][] parsePatternData() {
        return new Object[][]{
                {null, "[]"},
                {
                        "\"Имя собаки Герасима\",\"Муму\",\"Жужу\",\"Белка\",\"Стрелка\", \"Лайма, Фигайма\",1"
                        ,
                        "[Имя собаки Герасима, Муму, Жужу, Белка, Стрелка, Лайма, Фигайма, 1]"
                },
        };
    }

    @DataProvider
    public Object[][] parseStringData() {
        return new Object[][]{
                {null, "Question{question='', answers=[], rightAnswer=0}"},
                {
                        "\"Имя собаки Герасима\",\"Муму\",\"Жужу\",\"Белка\",\"Стрелка\", \"Лайма, Фигайма\",1"
                        ,
                        "Question{question='Имя собаки Герасима', answers=[Муму, Жужу, Белка, Стрелка, Лайма, Фигайма], rightAnswer=1}"
                },
        };
    }


    @Test(dataProvider = "parsePatternData")
    public void testParsePattern(String string, String expected) throws InvocationTargetException, IllegalAccessException {
        List<String> list = (List<String>) parsePattern.invoke(csvQuestionDao, string);
        assertEquals(list.toString(), expected);
    }


    @Test(dataProvider = "parseStringData")
    public void testParseString(String string, String expected) throws InvocationTargetException, IllegalAccessException {
        Question question = (Question) parseString.invoke(csvQuestionDao, string);
        assertEquals(question.toString(), expected);
    }

    @Test
    public void testReadAll() throws InvocationTargetException, IllegalAccessException {

        List<Question> questions = (List<Question>) readAll.invoke(csvQuestionDao);
        String out ="[Question{question='Имя собаки Герасима', answers=[Муму, Жужу, Белка, Стрелка, Лайма, Фигайма], rightAnswer=1}, Question{question='Имя создателя Linux', answers=[Adolf, Linus, Pingus, Richard, Batman], rightAnswer=2}]";
        assertEquals(questions.toString(), out);
    }
}