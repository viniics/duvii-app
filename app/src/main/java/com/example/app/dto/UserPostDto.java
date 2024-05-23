package com.example.app.dto;

import java.time.LocalTime;

import com.example.app.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserPostDto {
    @Email
    @NotNull
    private String email;
    @NotNull

    private String password;

    @NotNull
    private String userName;

    @NotNull
    private String nome;

    @NotNull
    private LocalTime creationTime;

    public User convertDtoToUser(){
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setUserName(userName);
            newUser.setNome(nome);
            newUser.setCreationTime(LocalTime.now());
            return newUser;
        }
}
