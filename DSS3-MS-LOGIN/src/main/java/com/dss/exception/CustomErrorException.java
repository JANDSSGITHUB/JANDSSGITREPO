package com.dss.exception;

public class CustomErrorException extends RuntimeException{
    public CustomErrorException(String message){
        super(message);
    }
}
