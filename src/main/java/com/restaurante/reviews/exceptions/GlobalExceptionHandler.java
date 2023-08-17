package com.restaurante.reviews.exceptions;

import com.restaurante.reviews.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDTO> ExceptionHandler(RuntimeException exception, WebRequest request, HttpStatus httpStatus) {

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(NotPermitsUserException.class)
    public ResponseEntity<ErrorDTO> handleNotPermitsUserException(NotPermitsUserException exception, WebRequest request) {

        return ExceptionHandler(exception, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerOrderNotFoundException(OrderNotFoundException exception, WebRequest request){

        return ExceptionHandler(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerUserNotFoundException(UserNotFoundException exception, WebRequest request){

        return ExceptionHandler(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerFoodNotFoundException(FoodNotFoundException exception, WebRequest request){

        return ExceptionHandler(exception, request, HttpStatus.NOT_FOUND);
    }
}
