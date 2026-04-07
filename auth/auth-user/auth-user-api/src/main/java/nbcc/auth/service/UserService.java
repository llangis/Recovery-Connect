package nbcc.auth.service;

import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ValidatedResult;

public interface UserService {

    ValidatedResult<UserResponse> register(UserRegistration userRegistration);

    ValidatedResult<UserResponse> get(String userName);

}
