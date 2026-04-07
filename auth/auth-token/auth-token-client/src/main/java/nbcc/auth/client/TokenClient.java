package nbcc.auth.client;

import nbcc.auth.domain.BearerToken;
import nbcc.common.result.ValidatedResult;

public interface TokenClient {
    ValidatedResult<BearerToken> validateToken(String token);
}
