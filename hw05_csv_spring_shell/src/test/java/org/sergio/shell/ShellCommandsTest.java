package org.sergio.shell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"})
class ShellCommandsTest {
    @Autowired
    private Shell shell;

    @BeforeEach
    void setUp() {
        Locale russian = new Locale("ru", "RU");
        Locale.setDefault(russian);
    }

    @Test
    void login() {
        Object result = shell.evaluate(new Input() {
            @Override
            public String rawText() {
                return "login Misha Gromov";
            }
        });
        assertEquals(result.toString(), "Добро пожаловать: Misha Gromov");
    }

    @Test
    void goodbye() {
        Object result = shell.evaluate(new Input() {
            @Override
            public String rawText() {
                return "g";
            }
        });
        assertEquals(result.toString(), "org.springframework.shell.CommandNotCurrentlyAvailable: Command 'g' exists but is not currently available because Сначала залогиньтесь");
        shell.evaluate(new Input() {
            @Override
            public String rawText() {
                return "login Misha Gromov";
            }
        });
        result = shell.evaluate(new Input() {
            @Override
            public String rawText() {
                return "g";
            }
        });
        assertEquals(result.toString(), "До свидания: Misha Gromov");

    }
}