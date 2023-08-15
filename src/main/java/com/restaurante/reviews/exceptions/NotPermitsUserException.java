package com.restaurante.reviews.exceptions;

public class NotPermitsUserException extends RuntimeException {

    public NotPermitsUserException(String message) {
        super(message);
    }
}
