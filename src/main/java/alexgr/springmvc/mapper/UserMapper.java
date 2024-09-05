package alexgr.springmvc.mapper;

import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class UserMapper {
    private final ObjectMapper mapper;

    public UserEntity toEntity(UserDTO userDTO) {
        return mapper.convertValue(userDTO, UserEntity.class);
    }

    public UserDTO toDto(UserEntity userEntity) {
        return mapper.convertValue(userEntity, UserDTO.class);
    }
}
