package nbcc.auth.mapper;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginDetails;
import nbcc.auth.entity.LoginEntity;
import nbcc.auth.entity.UserEntity;
import nbcc.common.mapper.RequestResponseEntityMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class BearerLoginMapper implements RequestResponseEntityMapper<LoginDetails, BearerToken, LoginEntity> {

    public BearerLoginMapper() {
    }

    @Override
    public BearerToken toDTO(LoginEntity loginEntity) {
        return new BearerToken()
                .setUserId(loginEntity.getUser().getId())
                .setName(loginEntity.getUser().getUsername())
                .setToken(loginEntity.getToken())
                .setTokenType("Bearer")
                .setExpiresIn(calculateExpiresIn(loginEntity));
    }

    @Override
    public LoginEntity toEntity(LoginDetails loginDetails) {
        var userEntity = new UserEntity();
        userEntity.setId(loginDetails.getUser().getId());

        return new LoginEntity()
                .setUser(userEntity)
                .setToken(loginDetails.getToken())
                .setLoginAt(loginDetails.getLoginAt())
                .setExpireAt(loginDetails.getExpireAt())
                .setLogoutAt(loginDetails.getLogoutAt());
    }

    private long calculateExpiresIn(LoginEntity loginEntity) {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), loginEntity.getExpireAt());
    }
}