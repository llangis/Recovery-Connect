package nbcc.auth.service;

import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.repository.UserRepository;
import nbcc.auth.validation.UserValidator;
import nbcc.common.result.ValidatedResult;
import nbcc.common.result.ValidationResults;
import nbcc.common.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserValidator userValidationService;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidationService) {
        this.userRepository = userRepository;
        this.userValidationService = userValidationService;
    }

    @Override
    public ValidatedResult<UserResponse> register(UserRegistration userRegistration) {
        try {
            var validationErrors = userValidationService.validate(userRegistration);

            if(validationErrors.isEmpty()){
                return ValidationResults.success(userRepository.create(userRegistration));
            }

            // don't call the create method if there are validation validationErrors
            logger.debug("Validation validationErrors for user registration {}: {}", userRegistration, validationErrors);
            return ValidationResults.invalid(new UserResponse(userRegistration), validationErrors);

        } catch (Exception e) {
            logger.error("Error registering user: {} , {}", userRegistration , e.getMessage());
            return ValidationResults.error(e);
        }
    }

    @Override
    public ValidatedResult<UserResponse> get(String userName) {
        try{
            var optionalUser = userRepository.get(userName);
            return optionalUser.map(ValidationResults::success)
                    .orElseGet(() -> ValidationResults.invalid(
                            new ValidationError("User not Found", null, userName)));

        } catch (Exception e) {
            logger.error("Error getting user: {}", userName, e);
            return ValidationResults.error(e);
        }
    }
}
