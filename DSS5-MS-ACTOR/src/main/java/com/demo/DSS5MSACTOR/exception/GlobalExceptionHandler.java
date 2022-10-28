package com.demo.DSS5MSACTOR.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice

public class GlobalExceptionHandler {



    @ExceptionHandler(NullValuesException.class)
    public ResponseEntity<?> handleNullValuesException(NullValuesException e, WebRequest request){
        ErrorMessage error = new ErrorMessage(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Null values");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e, WebRequest request){
        ErrorMessage error = new ErrorMessage(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "pat pogi");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
