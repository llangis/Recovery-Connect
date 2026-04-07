package nbcc.auth.service;

import nbcc.auth.client.UserClient;
import nbcc.auth.domain.LoginRequest;
import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.common.result.ClientResult;
import nbcc.common.result.ValidatedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserClientServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserClientServiceImpl.class);

    private final UserClient userClient;

    public UserClientServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }


    @Override
    public ValidatedResult<UserResponse> register(UserRegistration userRegistration) {
        try {
            return userClient.register(userRegistration);
        } catch (Exception e) {
            logger.error("Error registering user: {} , {}", userRegistration , e.getMessage());
            return ClientResult.error(e);
        }
    }

    @Override
    public ValidatedResult<UserResponse> get(String userName) {
        throw new UnsupportedOperationException("Not yet supported over api without including Roles support");
    }
}
