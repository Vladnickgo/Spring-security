package com.epam.esm.SpringSecurity.repository;

import com.epam.esm.SpringSecurity.repository.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudDao<Role,Integer>{
    Optional<Role> findByName(String name);
}
