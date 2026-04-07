package nbcc.auth.client;

import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ValidatedResult;

public interface UserClient {

    ValidatedResult<UserResponse> register(UserRegistration userRegistration);



    ValidatedResult<UserResponse> getProfile();
}
