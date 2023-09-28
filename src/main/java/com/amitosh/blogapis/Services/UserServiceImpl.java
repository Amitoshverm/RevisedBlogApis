package com.amitosh.blogapis.Services;


import com.amitosh.blogapis.Dtos.UserDto;
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
    public UserDto createUser(UserDto userDto) {
        User user = DtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserToDto(savedUser);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userList) {
            userDtos.add(UserToDto(user));
        }

        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return UserToDto(user);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, Long id) {
        User user = userRepository.findById(id).get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDescription(userDto.getDescription());

        User saveduser = userRepository.save(user);
        return UserToDto(saveduser);
    }

    @Override
    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "User with id "+ id +" is deleted !";
    }

    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDescription(userDto.getDescription());

        return user;
    }
    public UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setDescription(user.getDescription());

        return userDto;
    }
}
