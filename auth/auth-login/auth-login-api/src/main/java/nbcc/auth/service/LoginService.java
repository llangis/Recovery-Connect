package nbcc.auth.service;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ValidatedResult;

public interface LoginService {

    ValidatedResult<BearerToken> login(LoginRequest loginRequest);
    ValidatedResult<Boolean> logout(String token);
    ValidatedResult<UserResponse> isAuthorized(LoginRequest loginRequest);

}
