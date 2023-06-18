package com.epam.esm.SpringSecurity.service.mapper;

import com.epam.esm.SpringSecurity.repository.entity.Role;
import com.epam.esm.SpringSecurity.service.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapDtoToEntity(RoleDto roleDto);

    RoleDto mapEntityToDto(Role role);
}
