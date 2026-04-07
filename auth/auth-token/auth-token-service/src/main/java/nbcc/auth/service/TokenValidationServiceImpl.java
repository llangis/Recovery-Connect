package nbcc.auth.service;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.repository.LoginRepository;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {

    private final LoginRepository loginRepository;

    public TokenValidationServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public ValidatedResult<BearerToken> validateToken(String token) {

        var login = loginRepository.get(token);

        if (login != null) {
            if (login.isValid()) {
                return ValidationResults.success(login);
            }
        }

        return ValidationResults.invalid(new ValidationError("token is invalid"));
    }
}
