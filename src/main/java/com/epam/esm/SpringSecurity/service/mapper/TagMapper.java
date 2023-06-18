package com.epam.esm.SpringSecurity.service.mapper;

import com.epam.esm.SpringSecurity.repository.entity.Tag;
import com.epam.esm.SpringSecurity.service.dto.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag mapDtoToEntity(TagDto tagDto);

    TagDto mapEntityToDto(Tag tag);
}
