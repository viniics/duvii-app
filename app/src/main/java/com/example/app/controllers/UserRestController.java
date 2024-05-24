package com.example.app.controllers;

import com.example.app.dto.UserPostDto;
import com.example.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/user", produces = MediaType.APPLICATION_JSON_VALUE
)
@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600) // Substitua "porta" pela porta do seu front-end

public class UserRestController {
    UserService userService;
    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/post")
    ResponseEntity<?> createUser(
            @RequestBody @Valid UserPostDto userPostDto
            ){
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(userPostDto));
    }

    @GetMapping("/allUsers")
    ResponseEntity<?> createUser(){
    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getAllUsers());
}
}
