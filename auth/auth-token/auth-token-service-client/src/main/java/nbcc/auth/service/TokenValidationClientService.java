package nbcc.auth.service;

import nbcc.auth.client.TokenClient;
import nbcc.auth.domain.BearerToken;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationClientService implements TokenValidationService {

    private final Logger logger = LoggerFactory.getLogger(TokenValidationClientService.class);

    private final TokenClient loginClient;

    public TokenValidationClientService(TokenClient loginClient) {
        this.loginClient = loginClient;
    }

    @Override
    public ValidatedResult<BearerToken> validateToken(String token) {
        try {
            return loginClient.validateToken(token);
        } catch (Exception e) {
            logger.error("Error validating token", e);
            return ClientResult.error(e);
        }
    }
}
