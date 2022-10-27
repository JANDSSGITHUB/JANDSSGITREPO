package com.dss.exception;

public class NoRegisteredAccountException  extends RuntimeException{
    public NoRegisteredAccountException(String message){
        super(message);
    }
}
