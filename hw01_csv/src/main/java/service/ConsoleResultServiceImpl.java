package service;

public class ConsoleResultServiceImpl implements ResultService{
    PersonService personService;
    TestQuestionService testQuestionService;

    public ConsoleResultServiceImpl(PersonService personService, TestQuestionService testQuestionService) {
        this.personService = personService;
        this.testQuestionService = testQuestionService;
    }

    @Override
    public void writeHeader() {

    }

    @Override
    public void writeResult() {

    }
}
