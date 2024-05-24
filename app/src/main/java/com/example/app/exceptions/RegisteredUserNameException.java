package com.example.app.exceptions;

public class RegisteredUserNameException extends IllegalArgumentException{
    public RegisteredUserNameException(){
        super("There's already an account with this user name!");
    }
}