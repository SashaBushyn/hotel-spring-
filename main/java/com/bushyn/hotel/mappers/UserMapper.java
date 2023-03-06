package com.bushyn.hotel.mappers;

import com.bushyn.hotel.controller.dto.UserDto;
import com.bushyn.hotel.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface    UserMapper {
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User updateUser(@MappingTarget User user, UserDto userDto);
}
