package nbcc.auth.mapper;

import nbcc.auth.domain.UserRegistration;
import nbcc.auth.domain.UserResponse;
import nbcc.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toDTO(UserEntity entity) {

        if(entity == null){
            return null;
        }

        return new UserResponse()
                .setId(entity.getId())
                .setUsername(entity.getUsername())
                .setEmail(entity.getEmail())
                .setEnabled(entity.isEnabled())
                .setLocked(entity.isLocked())
                ;
    }


    @Override
    public UserEntity toEntity(UserRegistration request){

        if(request == null){
            return null;
        }

        return new UserEntity()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .setEmail(request.getEmail())
                .setEnabled(request.isEnabled())
                ;
    }

    @Override
    public UserEntity toEntity(UserResponse userresponse) {

        if(userresponse == null){
            return null;
        }

        return new UserEntity()
                .setId(userresponse.getId())
                .setUsername(userresponse.getUsername())
                .setEmail(userresponse.getEmail())
                .setEnabled(userresponse.isEnabled())
                .setLocked(userresponse.isLocked())
                ;
    }
}
