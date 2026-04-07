package nbcc.auth.controller.api;

import nbcc.auth.domain.*;
import nbcc.auth.service.UserService;
import nbcc.common.result.ValidatedResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static nbcc.common.result.ResultHandler.handleResult;


@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ValidatedResult<UserResponse>> register(@RequestBody UserRegistration userRegistration) {
        var result = userService.register(userRegistration);
        return handleResult(result, HttpStatus.CREATED);
    }
}
