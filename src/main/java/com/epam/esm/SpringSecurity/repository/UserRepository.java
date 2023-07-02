package com.epam.esm.SpringSecurity.repository;


import com.epam.esm.SpringSecurity.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserRepository extends CrudDao<User, Integer> {
    Page<User> findByLastNameContainsIgnoreCase(String name, Pageable pageable);

        Optional<User> findByUsername(String username);
}
