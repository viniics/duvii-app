package com.example.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.UserPostDto;
import com.example.app.entity.User;
import com.example.app.exceptions.RegisteredEmailException;
import com.example.app.exceptions.RegisteredUserNameException;
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
        checkAlreadyRegistered(userDto.getEmail(), userDto.getUserName());
        userRepository.save(newUser);
        return newUser;
    }

    public User retreiveUser(String query){
        User user = userRepository.findByEmail(query);
        if (user == null){
            user = userRepository.findByUserName(query);
        }
        if (user == null) throw new RuntimeErrorException(null, "Theres no one,blablabla !");
        return user;
    }

    private void checkAlreadyRegistered(String email, String userName) {
        if(userRepository.existsByUserName(userName)){
            throw new RegisteredUserNameException();
        }
         if(userRepository.existsByEmail(email)){
            throw new RegisteredEmailException();
         }
    }
    public String getAllUsers(){
        List<User> allUsers = userRepository.findAll();
        String out = "";
        for(User user: allUsers){
            out+= user.getEmail()+ " - " +user.getNome() +"\n";
        }
        return out;
    }
}
