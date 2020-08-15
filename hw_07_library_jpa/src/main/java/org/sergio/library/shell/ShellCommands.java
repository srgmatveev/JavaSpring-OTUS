package org.sergio.library.shell;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.service.LibraryService;
import org.sergio.library.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@ShellComponent
public class ShellCommands {
    private final PersonService personService;
    private final MessageSource ms;
    private String userName;
    private String userSurName;
    private final LibraryService ls;

    public ShellCommands(
            @Qualifier("shellPersonService") PersonService personService, MessageSource ms,
            @Qualifier("shellLibService") LibraryService ls) {
        this.personService = personService;
        this.ms = ms;
        this.ls = ls;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    @ShellMethodAvailability(value = "isAlreadyLogin")
    public String login(@ShellOption(defaultValue = "Sergio") String userName, @ShellOption(defaultValue = "Matveev") String userSurName)
            throws Exception {
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

    @ShellMethod(value = "List Books by Genres command", key = {"gr", "genre"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String allGenres() {
        StringBuilder builder = new StringBuilder();
        Map<Genre, List<Book>> map = ls.getBooksForAllGenres();
        for (Map.Entry<Genre, List<Book>> entry : map.entrySet()) {
            builder.append(entry.getKey().getGenreName());
            builder.append(":\n");
            List<Book> books = entry.getValue();
            if (books.size() > 0)
                for (Book book : books) {
                    builder.append("\t");
                    builder.append(book.getBookName());
                    builder.append("\n");
                }
            else {
                builder.append("\t");
                builder.append(ms.getMessage("shell.books.genre.not.present", null, Locale.getDefault()));
                builder.append("\n");
            }

        }
        return builder.toString();
    }


    private Availability isPublishEventCommandAvailable() {
        return userName == null || userName.isBlank() || userSurName == null || userSurName.isBlank()
                ? Availability.unavailable(ms.getMessage("shell.notlogin", null, Locale.getDefault())) : Availability.available();
    }

    private Availability isAlreadyLogin() {
        if (userName != null || userSurName != null) {
            return Availability.unavailable(ms.getMessage("shell.already.login", null, Locale.getDefault()));
        } else
            return Availability.available();
    }

}
