package nbcc.recovery.controller;

import nbcc.common.service.CurrentLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentLoginService loginService;

    public HomeController(CurrentLoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn());
        model.addAttribute("username", loginService.getCurrentUsername());
        return "index";
    }
}
