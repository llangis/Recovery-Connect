package nbcc.auth.controller.api;

import nbcc.auth.domain.BearerToken;
import nbcc.common.result.ValidatedResult;
import nbcc.auth.service.TokenValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static nbcc.common.result.ResultHandler.handleResult;

@RestController
@RequestMapping("/api/auth")
public class TokenApiController {

    private final TokenValidationService tokenValidationService;

    public TokenApiController(TokenValidationService tokenValidationService) {
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping("validate")
    public ResponseEntity<ValidatedResult<BearerToken>> validateToken(@RequestBody String token) {

        var result = tokenValidationService.validateToken(token);
        return handleResult(result, HttpStatus.OK, HttpStatus.UNAUTHORIZED);
    }
}
