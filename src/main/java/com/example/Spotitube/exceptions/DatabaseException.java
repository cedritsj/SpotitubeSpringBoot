package com.example.Spotitube.exceptions;

public class DatabaseException extends SpotitubeException {
    public DatabaseException(String message) {
        super(message, 500);
    }
}