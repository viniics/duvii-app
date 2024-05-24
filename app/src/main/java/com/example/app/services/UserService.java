package com.example.app.services;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.UserPostDto;
import com.example.app.entity.User;
import com.example.app.exceptions.RegisteredEmailException;
import com.example.app.repositories.user.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(UserPostDto userDto){
        User newUser = userDto.convertDtoToUser();
        if(alreadyRegistered(userDto.getEmail())) throw new RegisteredEmailException();
        userRepository.save(newUser);
        return newUser;
    }

    public User retrieveUser(String query){
        User user = userRepository.findByEmail(query);
        if (user == null){
            user = userRepository.findByUserName(query);
        }
        if (user == null) throw new RuntimeErrorException(null, "Theres no one,blablabla !");
        return user;
    }

    private boolean alreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
}
