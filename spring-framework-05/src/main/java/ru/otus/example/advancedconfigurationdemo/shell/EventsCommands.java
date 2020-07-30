package ru.otus.example.advancedconfigurationdemo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.example.advancedconfigurationdemo.events.EventsPublisher;

@Profile("shell")
@ShellComponent
@RequiredArgsConstructor
public class EventsCommands {

    private final EventsPublisher eventsPublisher;

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "stvort") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Goodbye command", key = {"g", "goodbye"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String goodbye() {
        String ret = null;
        if (userName != null) {
            ret = String.format("До свидания: %s", userName);
            userName = null;
        }
        return ret;
    }


    @ShellMethod(value = "Publish event command", key = {"p", "pub", "publish"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void publishEvent() {
        eventsPublisher.publish();
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
