package org.sergio.library.controllres;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String login(Model model) {
        return "login/loginPage";
    }

    @GetMapping("/loginPageError")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        model.addAttribute("errorText", "Ошибка входа");
        return "login/loginPage";
    }

    @GetMapping("/logoutPage")
    public String logout(Model model) {
        return "login/logoutPage";
    }
}
