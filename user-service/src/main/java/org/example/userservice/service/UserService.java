package org.example.userservice.service;

import org.example.userservice.dto.UserDto;
import org.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(final UserDto userDto);
    UserDto getUserByUserId(final String userId);
    Iterable<UserEntity> getUserByAll();
    UserDto getUserDetailsByEmail(String email);
}
