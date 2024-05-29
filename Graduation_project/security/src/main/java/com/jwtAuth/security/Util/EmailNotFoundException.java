package com.jwtAuth.security.Util;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String message){
        super(message);
    }
    public EmailNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public EmailNotFoundException(Throwable cause){
        super(cause);
    }
}
