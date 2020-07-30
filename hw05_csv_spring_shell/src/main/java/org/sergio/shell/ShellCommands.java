package org.sergio.shell;

import org.sergio.service.AskQuestionService;
import org.sergio.service.PersonService;
import org.sergio.service.PersonServiceImpl;
import org.sergio.service.ResultService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class ShellCommands {
    private PersonService personService;
    private String userName;
    private String userSurName;
    private ApplicationContext context;

    public ShellCommands(@Qualifier("shellPersonService") PersonService personService, ApplicationContext context) {
        this.personService = personService;
        this.context = context;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Sergio") String userName, @ShellOption(defaultValue = "Matveev") String userSurName) throws Exception {
        this.userName = userName;
        this.userSurName = userSurName;
        personService.login(userName, userSurName);
        return String.format("Добро пожаловать: %s %s", userName, userSurName);
    }

    @ShellMethod(value = "Goodbye command", key = {"g", "goodbye"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String goodbye() {
        String ret = null;
        if ((userName != null && userSurName != null) || (!userName.isBlank() && !userSurName.isBlank())) {
            ret = String.format("До свидания: %s %s", userName, userSurName);
            userName = null;
            userSurName = null;
        }
        return ret;
    }

    @ShellMethod(value = "Test comman", key = {"t", "test"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void test() throws IOException {
        ResultService resultService = (ResultService) context.getBean("resultShellService");
        AskQuestionService questionService = (AskQuestionService) context.getBean("askQuestionService");
        if (resultService != null) {
            resultService.writeHeader();
            questionService.askQuestions();
            resultService.writeResult();
        }
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null || userName.isBlank() || userSurName == null || userSurName.isBlank() ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }

}
