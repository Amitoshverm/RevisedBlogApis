package com.amitosh.blogapis.Services;


import com.amitosh.blogapis.Enitities.User;
import com.amitosh.blogapis.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        User user1 = new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setDescription(user.getDescription());
        return userRepository.save(user1);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUserById(Long id, User user) {

        User editedUser = new User();

        editedUser.setName(user.getName());
        editedUser.setEmail(user.getEmail());
        editedUser.setPassword(user.getPassword());
        editedUser.setDescription(user.getDescription());

        User savedUser = userRepository.save(editedUser);

        return savedUser;
    }

    @Override
    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "User with id "+ id +" is deleted !";
    }
}
