package nbcc.auth.service;

import nbcc.auth.domain.BearerToken;
import nbcc.common.result.ValidatedResult;

public interface TokenValidationService {
    ValidatedResult<BearerToken> validateToken(String token);
}
