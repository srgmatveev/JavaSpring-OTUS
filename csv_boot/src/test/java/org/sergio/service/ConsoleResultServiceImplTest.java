package org.sergio.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sergio.domain.Question;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsoleResultServiceImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @Mock
    private PersonService personService;

    @Mock
    private MessageSource ms;

    @Mock
    private AskQuestionService askQuestionService;

    private
    ResultService resultService;

    @BeforeEach
    void setUp() {
        resultService = new ConsoleResultServiceImpl(personService, askQuestionService, ms);
        Mockito.when(personService.writeName()).thenReturn("Sergio");
        Mockito.when(personService.writeSurname()).thenReturn("Matveev");
        Mockito.when(personService.writeFullName()).thenReturn("Sergio Matveev");
        Mockito.when(ms.getMessage("result.user.pattern", null, Locale.getDefault())).
                thenReturn("Resultat of the user test: %s");
        Mockito.when(ms.getMessage("result.final.right", null, Locale.getDefault())).
                thenReturn(" Right.");
        Mockito.when(ms.getMessage("result.final.wrong", null, Locale.getDefault())).
                thenReturn(" Wrong.");
        Question question = new Question("Where was born Napoleon?", Arrays.asList("Russia", "Germany", "England", "France", "Italy"), 4);
        Map<Question, Integer> map = new HashMap<>();
        map.put(question, 4);
        Mockito.when(askQuestionService.getResult()).thenReturn(map);

        Integer[] arr = {1, 1, 0};
        Mockito.when(ms.getMessage("result.final.pattern", arr, Locale.getDefault())).
                thenReturn("All questions 1. Right: 1. Wrong: 0.");

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void writeHeader() {
        resultService.writeHeader();
        assertEquals("Sergio\nMatveev\n", outContent.toString());
    }

    @Test
    void writeResult() {
        resultService.writeResult();
        assertEquals("\n" +
                "Resultat of the user test: Sergio Matveev\n" +
                "Where was born Napoleon? Right.\n" +
                "All questions 1. Right: 1. Wrong: 0.\n", outContent.toString());
    }
}