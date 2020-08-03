package org.sergio.library.shell;

import org.sergio.library.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

@ShellComponent
public class ShellCommands {
    private PersonService personService;
    private String userName;
    private String userSurName;
    private ApplicationContext context;
    private MessageSource ms;

    public ShellCommands(@Qualifier("shellPersonService") PersonService personService, MessageSource ms, ApplicationContext context) {
        this.personService = personService;
        this.ms = ms;
        this.context = context;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Sergio") String userName, @ShellOption(defaultValue = "Matveev") String userSurName) throws Exception {
        this.userName = userName;
        this.userSurName = userSurName;
        personService.login(userName, userSurName);
        return String.format(ms.getMessage("shell.login", null, Locale.getDefault()), userName, userSurName);
    }

    @ShellMethod(value = "Goodbye command", key = {"g", "goodbye"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String goodbye() {
        String ret = null;
        if ((userName != null && userSurName != null) || (!userName.isBlank() && !userSurName.isBlank())) {
            ret = String.format(ms.getMessage("shell.logout", null, Locale.getDefault()), userName, userSurName);
            userName = null;
            userSurName = null;
        }
        return ret;
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null || userName.isBlank() || userSurName == null || userSurName.isBlank()
                ?  Availability.unavailable(ms.getMessage("shell.notlogin", null, Locale.getDefault())) : Availability.available();
    }
}
