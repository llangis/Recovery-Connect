package nbcc.auth.repository;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginDetails;
import nbcc.auth.mapper.BearerLoginMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class LoginRepositoryAdapter implements LoginRepository{

    private final LoginJPRepository loginJPRepository;
    private final UserJPARepository userJPARepository;
    private final BearerLoginMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public LoginRepositoryAdapter(LoginJPRepository loginJPRepository, UserJPARepository userJPARepository, BearerLoginMapper mapper, PasswordEncoder passwordEncoder) {
        this.loginJPRepository = loginJPRepository;
        this.userJPARepository = userJPARepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BearerToken get(String token) {
        var optionalLoginEntity = loginJPRepository.findByToken(token);
        return optionalLoginEntity.map(mapper::toDTO).orElse(null);
    }

    @Override
    public BearerToken create(LoginDetails loginDetails) {
        var entity = mapper.toEntity(loginDetails);
        entity = loginJPRepository.save(entity);
        return mapper.toDTO(entity);
    }

    @Override
    public boolean logout(String token) {
        throw new UnsupportedOperationException("Todo implement");
    }

    @Override
    public boolean isValid(String username, String password) {
        var optionalUser = userJPARepository.findByUsernameIgnoreCase(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();

            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
