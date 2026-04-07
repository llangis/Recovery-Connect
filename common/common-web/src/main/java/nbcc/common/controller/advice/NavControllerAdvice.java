package nbcc.common.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import nbcc.common.service.CurrentLoginService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class NavControllerAdvice {

    private final CurrentLoginService currentLoginService;

    public NavControllerAdvice(CurrentLoginService currentLoginService) {
        this.currentLoginService = currentLoginService;
    }

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn() {
        return currentLoginService.isLoggedIn();
    }

    @ModelAttribute("username")
    public String username() {
        return currentLoginService.getCurrentUsername();
    }
    @ModelAttribute("currentURI")
    public String currentURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
}