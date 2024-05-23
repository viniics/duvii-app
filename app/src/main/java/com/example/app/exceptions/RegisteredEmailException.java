package com.example.app.exceptions;

public class RegisteredEmailException extends IllegalArgumentException{
    public RegisteredEmailException(){
        super("There's already an account associated with this Email!");
    }
}