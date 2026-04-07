package nbcc.auth.service;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.repository.LoginRepository;
import nbcc.auth.validation.LoginRequestValidationService;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoginServiceImpl implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final LoginRequestValidationService loginRequestValidationService;
    private final TokenCreationService tokenCreationService;

    private final LoginRepository loginRepository;
    private final UserService userService;

    public LoginServiceImpl(LoginRequestValidationService loginRequestValidationService, TokenCreationService tokenCreationService, LoginRepository loginRepository, UserService userService) {
        this.loginRequestValidationService = loginRequestValidationService;
        this.tokenCreationService = tokenCreationService;
        this.loginRepository = loginRepository;
        this.userService = userService;
    }


    @Override
    public ValidatedResult<BearerToken> login(LoginRequest loginRequest) {

        try {
            var userResult = isAuthorized(loginRequest);

            if (userResult.isInvalid()) {
                return ValidationResults.invalid(userResult.getValidationErrors());
            }

            if (userResult.isSuccessful() && userResult.hasValue()) {
                var tokenResult = tokenCreationService.createToken(userResult.getValue());
                if (tokenResult.isSuccessful() && tokenResult.hasValue()) {
                    return ValidationResults.success(tokenResult.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("Error logging in user: {}", loginRequest.getUsername(), e);
        }

        return ValidationResults.error();
    }

    @Override
    public ValidatedResult<Boolean> logout(String token) {
        try {
            var loggedOut = loginRepository.logout(token);
            return ValidationResults.success(loggedOut);
        } catch (Exception e) {
            logger.error("Error logging out user", e);
        }

        return ValidationResults.error();
    }

    @Override
    public ValidatedResult<UserResponse> isAuthorized(LoginRequest loginRequest) {

        var errors = loginRequestValidationService.validate(loginRequest);

        if(!errors.isEmpty()){
            return ValidationResults.invalid(errors);
        }

        try {
            var isValid = loginRepository.isValid(loginRequest.getUsername(), loginRequest.getPassword());

            if(isValid){
                var userResult = userService.get(loginRequest.getUsername());
                if(userResult.isSuccessful() && userResult.hasValue()){
                    var user = userResult.getValue();
                    if(user.isEnabled() && !user.isLocked()) {
                        return ValidationResults.success(user);
                    } else {
                        return ValidationResults.invalid(new ValidationError("User is not enabled or locked"));
                    }
                }
            }

            return ValidationResults.invalid(new ValidationError("Invalid username or password"));

        } catch (Exception e) {
            logger.error("Error checking user authorization: {} , {}", loginRequest.getUsername(), e.getMessage());
            return ValidationResults.error(e);
        }
    }
}