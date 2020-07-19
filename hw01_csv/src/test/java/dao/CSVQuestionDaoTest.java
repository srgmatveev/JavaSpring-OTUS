package dao;

import org.sergio.domain.Question;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.testng.Assert.*;

public class CSVQuestionDaoTest extends Assert {
    private Method parsePattern;
    private CSVQuestionDao csvQuestionDao;
    private Method parseString;

    @BeforeMethod
    public void setUp() throws NoSuchMethodException {
        csvQuestionDao = new CSVQuestionDao();
        parsePattern = csvQuestionDao.getClass().getDeclaredMethod("parsePattern", String.class);
        parsePattern.setAccessible(true);
        parseString = csvQuestionDao.getClass().getDeclaredMethod("parseString", String.class);
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
}