package com.restaurante.reviews.exceptions;

public class AuthenticationErrorException extends RuntimeException{
    public AuthenticationErrorException(String message) {
        super(message);
    }
}
