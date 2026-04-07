package nbcc.auth.mapper;

import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.entity.UserEntity;
import nbcc.common.mapper.RequestResponseEntityMapper;

public interface UserMapper extends RequestResponseEntityMapper<UserRegistration, UserResponse, UserEntity> {
        UserEntity toEntity(UserResponse userresponse);
}
