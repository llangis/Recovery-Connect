package nbcc.auth.service;

import nbcc.auth.client.LoginClient;
import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginClientServiceImpl implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginClientServiceImpl.class);

    private final LoginClient loginClient;

    public LoginClientServiceImpl(LoginClient loginClient) {
        this.loginClient = loginClient;
    }

    @Override
    public ValidatedResult<BearerToken> login(LoginRequest loginRequest) {
        try {
            return loginClient.login(loginRequest);
        } catch (Exception e) {
            logger.error("Error creating token for user: {}", loginRequest.getUsername(), e);
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<Boolean> logout(String token) {
        try {
            return loginClient.logout(token);
        } catch (Exception e) {
            logger.error("Error logging out user", e);
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<UserResponse> isAuthorized(LoginRequest loginRequest) {
        try {
            return loginClient.isAuthorized(loginRequest);
        } catch (Exception e) {
            logger.error("Error validating user: {}", loginRequest.getUsername(), e);
            return ClientResult.error(e);
        }
    }
}
