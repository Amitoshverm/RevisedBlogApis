package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Dtos.UserDto;

import java.util.List;

public interface UserServices {

    UserDto createUser(UserDto user);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUserById(UserDto user, Long id);
    String deleteUserById(Long id);
}
