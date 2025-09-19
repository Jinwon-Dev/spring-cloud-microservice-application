package org.example.userservice.service;

import org.example.userservice.dto.UserDto;
import org.example.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(final UserDto userDto);
    public Iterable<UserEntity> getUserByAll();

}
