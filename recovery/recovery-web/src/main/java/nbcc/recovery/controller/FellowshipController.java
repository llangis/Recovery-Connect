package nbcc.recovery.controller;

import nbcc.common.service.CurrentLoginService;
import nbcc.recovery.dto.FellowshipType;
import nbcc.recovery.service.PromiseService;
import nbcc.recovery.service.StepService;
import nbcc.recovery.service.TraditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{fellowship}")
public class FellowshipController {

    private final Logger logger = LoggerFactory.getLogger(FellowshipController.class);
    private final CurrentLoginService loginService;
    private final StepService stepService;
    private final TraditionService traditionService;
    private final PromiseService promiseService;

    public FellowshipController(CurrentLoginService loginService, StepService stepService,
                                TraditionService traditionService, PromiseService promiseService) {
        this.loginService = loginService;
        this.stepService = stepService;
        this.traditionService = traditionService;
        this.promiseService = promiseService;
    }

    @GetMapping
    public String home(@PathVariable String fellowship, Model model) {
        var type = parseFellowship(fellowship);
        if (type == null) return "error/404";
        addCommonAttributes(model, type);
        return fellowship.toLowerCase() + "/home";
    }

    @GetMapping("/steps")
    public String steps(@PathVariable String fellowship, Model model) {
        var type = parseFellowship(fellowship);
        if (type == null) return "error/404";
        var result = stepService.getByFellowship(type);
        if (result.isError()) {
            model.addAttribute("message", "Error retrieving steps");
            return "error/errorPage";
        }
        model.addAttribute("steps", result.getValue());
        addCommonAttributes(model, type);
        return fellowship.toLowerCase() + "/steps";
    }

    @GetMapping("/traditions")
    public String traditions(@PathVariable String fellowship, Model model) {
        var type = parseFellowship(fellowship);
        if (type == null) return "error/404";
        var result = traditionService.getByFellowship(type);
        if (result.isError()) {
            model.addAttribute("message", "Error retrieving traditions");
            return "error/errorPage";
        }
        model.addAttribute("traditions", result.getValue());
        addCommonAttributes(model, type);
        return fellowship.toLowerCase() + "/traditions";
    }

    @GetMapping("/promises")
    public String promises(@PathVariable String fellowship, Model model) {
        var type = parseFellowship(fellowship);
        if (type == null) return "error/404";
        var result = promiseService.getByFellowship(type);
        if (result.isError()) {
            model.addAttribute("message", "Error retrieving promises");
            return "error/errorPage";
        }
        model.addAttribute("promises", result.getValue());
        addCommonAttributes(model, type);
        return fellowship.toLowerCase() + "/promises";
    }

    private FellowshipType parseFellowship(String fellowship) {
        try {
            return FellowshipType.valueOf(fellowship.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid fellowship type: {}", fellowship);
            return null;
        }
    }

    private void addCommonAttributes(Model model, FellowshipType type) {
        model.addAttribute("fellowship", type.name().toLowerCase());
        model.addAttribute("fellowshipType", type);
        model.addAttribute("fellowshipName", getFellowshipFullName(type));
        model.addAttribute("isLoggedIn", loginService.isLoggedIn());
        model.addAttribute("username", loginService.getCurrentUsername());
    }

    private String getFellowshipFullName(FellowshipType type) {
        return switch (type) {
            case AA -> "Alcoholics Anonymous";
            case NA -> "Narcotics Anonymous";
            case CA -> "Cocaine Anonymous";
        };
    }
}
