package com.dss.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<?> CustomErrorException(CustomErrorException e, WebRequest request){
        ErrorMessage error = new ErrorMessage(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NoRegisteredAccountException.class)
    public ResponseEntity<?> handleRegisterNoFoundException(NoRegisteredAccountException e, WebRequest request){
        ErrorMessage error = new ErrorMessage("No Registered Account!",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Logical Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateUserException e, WebRequest request){
        ErrorMessage error = new ErrorMessage("Email has already been used",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Logical Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NullValuesException.class)
    public ResponseEntity<?> handleNullValuesException(NullValuesException e, WebRequest request){
        ErrorMessage error = new ErrorMessage(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Null values");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e, WebRequest request){
        ErrorMessage error = new ErrorMessage(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
