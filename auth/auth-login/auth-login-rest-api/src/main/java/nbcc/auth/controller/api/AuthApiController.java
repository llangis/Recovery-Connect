package nbcc.auth.controller.api;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.service.LoginService;
import nbcc.common.result.ValidatedResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static nbcc.common.result.ResultHandler.handleResult;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final LoginService loginService;

    public AuthApiController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ValidatedResult<BearerToken>> login(@RequestBody LoginRequest loginRequest) {
        var result = loginService.login(loginRequest);
        return handleResult(result, HttpStatus.CREATED, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("logout")
    public ResponseEntity<ValidatedResult<Boolean>> logout(@RequestBody String token) {
        var result = loginService.logout(token);
        return handleResult(result, HttpStatus.OK, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authorize")
    public ResponseEntity<ValidatedResult<UserResponse>> authorize(@RequestBody LoginRequest loginRequest) {
        var result = loginService.isAuthorized(loginRequest);
        return handleResult(result, HttpStatus.OK, HttpStatus.UNAUTHORIZED);
    }
}