package com.epam.esm.SpringSecurity.service.mapper;

import com.epam.esm.SpringSecurity.repository.entity.User;
import com.epam.esm.SpringSecurity.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleId")
    UserDto mapEntityToDto(User user);

    @Mapping(source = "roleId", target = "role.id")
    User mapDtoToEntity(UserDto userDto);
}

