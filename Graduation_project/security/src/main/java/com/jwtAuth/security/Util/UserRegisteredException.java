package com.jwtAuth.security.Util;

public class UserRegisteredException extends RuntimeException{
    public UserRegisteredException(String message){
        super(message);
    }
    public UserRegisteredException(String message,Throwable cause){
        super(message,cause);
    }
    public UserRegisteredException(Throwable cause){
        super(cause);
    }
}
