package com.epam.esm.SpringSecurity.service.impl;

import com.epam.esm.SpringSecurity.exception.NotFoundException;
import com.epam.esm.SpringSecurity.repository.entity.User;
import com.epam.esm.SpringSecurity.repository.impl.UserRepositoryImpl;
import com.epam.esm.SpringSecurity.service.UserService;
import com.epam.esm.SpringSecurity.service.dto.UserDto;
import com.epam.esm.SpringSecurity.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private Integer countAll() {
        return userRepository.countAll();
    }

    private Integer countAllByName(String name) {
        Optional<Integer> countAllByName = userRepository.countAllByName(name);
        return countAllByName.orElse(0);

    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        int total = countAll();
        Page<User> userRepositoryAll = userRepository.findAll(pageable);
        List<UserDto> userDtoList = userRepositoryAll.stream().map(userMapper::mapEntityToDto).toList();
        log.info("IN findAll - {} users found", total);
        return new PageImpl<>(userDtoList, pageable, total);
    }

    @Override
    public UserDto findById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new NotFoundException("User with id=" + id + " not found"));
        log.info("IN findById user with id {} was found",id);
        return userMapper.mapEntityToDto(user);
    }

    @Override
    public Page<UserDto> findByName(String name, Pageable pageable) {
        Integer total = countAllByName(name);
        List<UserDto> userDtoList = userRepository.findByLastNameContainsIgnoreCase(name, pageable).stream()
                .map(userMapper::mapEntityToDto)
                .toList();
        return new PageImpl<>(userDtoList, pageable, total);
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = userMapper.mapDtoToEntity(userDto);
        userRepository.save(user);
        User lastAdded = userRepository.findLastAdded().orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.mapEntityToDto(lastAdded);
    }
}
