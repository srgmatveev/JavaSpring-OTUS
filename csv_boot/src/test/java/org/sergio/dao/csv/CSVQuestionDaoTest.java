package org.sergio.dao.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.dao.QuestionDao;
import org.sergio.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration("classpath:org/sergio/dao/csv/csv-test-context.xml")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CSVQuestionDaoTest {
    @BeforeEach
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