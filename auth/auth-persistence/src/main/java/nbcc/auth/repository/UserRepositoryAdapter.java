package nbcc.auth.repository;

import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserJPARepository userJPARepository;

    public UserRepositoryAdapter(PasswordEncoder passwordEncoder, UserMapper userMapper, UserJPARepository userJPARepository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userJPARepository = userJPARepository;
    }

    @Override
    public UserResponse create(UserRegistration userLogin) {
        userLogin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        var entity = userMapper.toEntity(userLogin);
        entity = userJPARepository.save(entity);
        return userMapper.toDTO(entity);
    }

    @Override
    public Optional<UserResponse> get(long id) {
        var entity = userJPARepository.findById(id);
        return userMapper.toDTO(entity);
    }

    @Override
    public Optional<UserResponse> get(String username) {
        var entity = userJPARepository.findByUsernameIgnoreCase(username);
        return userMapper.toDTO(entity);
    }

    @Override
    public boolean userExists(String username) {
        return userJPARepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userJPARepository.existsByEmailIgnoreCase(email);
    }
}