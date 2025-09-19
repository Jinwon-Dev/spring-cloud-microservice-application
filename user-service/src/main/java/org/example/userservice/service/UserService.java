package org.example.userservice.service;

import org.example.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(final UserDto userDto);
}
