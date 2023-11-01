package com.example.Spotitube.exceptions;

public class InvalidCredentialsException extends SpotitubeException {
    public InvalidCredentialsException() {
        super("Invalid credentials", 403);
    }
}