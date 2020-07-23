package org.sergio.dao.csv;

import org.sergio.dao.QuestionDao;
import org.sergio.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

import static org.testng.Assert.*;

@ContextConfiguration("classpath:org/sergio/dao/csv/csv-test-context.xml")
public class CSVQuestionDaoTest extends AbstractTestNGSpringContextTests {
    @BeforeMethod
    public void setUp() {
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    public void testReadAll() {
        List<Question> questionList = questionDao.readAll();
        String test = "[Question{question='Имя собаки Герасима', answers=[Муму, Жужу, Белка, Стрелка, Лайма, Фигайма], rightAnswer=1}, Question{question='Имя создателя Linux', answers=[Adolf, Linus, Pingus, Richard, Batman], rightAnswer=2}]";
        assertEquals(questionList.size(),2);
        assertEquals(questionList.toString(), test);
    }

    @Autowired
    private QuestionDao questionDao;
}