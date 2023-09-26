package com.amitosh.blogapis.Services;

import com.amitosh.blogapis.Enitities.User;

import java.util.List;

public interface UserServices {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUserById(Long id, User user);
    String deleteUserById(Long id);
}
