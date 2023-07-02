package com.epam.esm.SpringSecurity.repository.impl;

import com.epam.esm.SpringSecurity.repository.RoleRepository;
import com.epam.esm.SpringSecurity.repository.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private static final String FIND_BY_NAME = "SELECT * FROM roles WHERE name = ?; ";
    private static final String FIND_BY_ID = "SELECT * FROM roles WHERE id = ?;";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Role entity) {

    }

    @Override
    public Optional<Role> findById(Integer integer) {
        return getRoleBytParameter(FIND_BY_ID, integer);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Integer integer, Role entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Optional<Role> findByName(String name) {
        return getRoleBytParameter(FIND_BY_NAME, name);
    }

    private <T> Optional<Role> getRoleBytParameter(String query, T parameter) {
        try {
            Role role = jdbcTemplate.queryForObject(query, (rs, rowNum) -> Role.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build(), parameter);
            return Optional.ofNullable(role);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
