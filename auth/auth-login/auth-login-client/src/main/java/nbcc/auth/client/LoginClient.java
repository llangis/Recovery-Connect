package nbcc.auth.client;

import nbcc.auth.domain.*;
import nbcc.common.result.ValidatedResult;

public interface LoginClient {

    ValidatedResult<BearerToken> login(LoginRequest loginRequest);

    ValidatedResult<Boolean> logout(String token);

    ValidatedResult<UserResponse> isAuthorized(LoginRequest loginRequest);
}
