package com.nutritionist.api.model.mapper;

import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "username",target = "username"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "password",target = "password")})
    UserEntity toUser(UserDto userDTO);
}
