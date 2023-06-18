package com.epam.esm.SpringSecurity.repository;


import com.epam.esm.SpringSecurity.repository.entity.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TagRepository extends CrudDao<Tag, Integer> {
    Tag findByName(String tagName);

    List<Tag> findByNameContainingIgnoreCase(String partOfName, Pageable pageable);

    Tag findMostWidelyUsedTag();

    Optional<Tag> findById(Integer id);

    Integer countAll();
}

