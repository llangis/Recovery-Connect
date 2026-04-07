package nbcc.auth.service;


import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ValidatedResult;

public interface TokenCreationService {

    ValidatedResult<BearerToken> createToken(UserResponse userResponse);
}
