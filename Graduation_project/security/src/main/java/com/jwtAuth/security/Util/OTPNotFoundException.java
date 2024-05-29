package com.jwtAuth.security.Util;

public class OTPNotFoundException extends RuntimeException{
    public OTPNotFoundException(String message){
        super(message);
    }
    public OTPNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public OTPNotFoundException(Throwable cause){
        super(cause);
    }
}
