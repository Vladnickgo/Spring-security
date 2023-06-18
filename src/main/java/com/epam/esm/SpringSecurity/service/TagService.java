package com.epam.esm.SpringSecurity.service;

import com.epam.esm.SpringSecurity.service.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    TagDto findById(Integer id);

    TagDto save(TagDto tagDto);

    Page<TagDto> findAll(Pageable pageable);

    Page<TagDto> findAllByTagName(String tagName, Pageable pageable);

    TagDto update(Integer id, TagDto tagDto);

    TagDto findMostWidelyUsedTag();
}
