package com.example.Spotitube.exceptions;

public class AuthenticationException extends SpotitubeException{
    public AuthenticationException() {
        super("Authentication failed", 401);
    }
}