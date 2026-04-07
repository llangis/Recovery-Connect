package nbcc.auth.service;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginDetails;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.repository.LoginRepository;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.auth.config.BearerConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenCreationServiceImpl implements TokenCreationService {

    private final BearerConfig bearerConfig;
    private final LoginRepository loginRepository;

    public TokenCreationServiceImpl(BearerConfig bearerConfig, LoginRepository loginRepository) {
        this.bearerConfig = bearerConfig;
        this.loginRepository = loginRepository;
    }

    @Override
    public ValidatedResult<BearerToken> createToken(UserResponse userResponse) {

        var loginAt = LocalDateTime.now();
        var expireAt = loginAt.plusSeconds(bearerConfig.getTimeout());

        var loginDetails = new LoginDetails()
                .setUser(userResponse)
                .setLoginAt(loginAt)
                .setExpireAt(expireAt)
                .setToken(UUID.randomUUID().toString());

        var bearer = loginRepository.create(loginDetails);
        return ValidationResults.success(bearer);
    }
}
