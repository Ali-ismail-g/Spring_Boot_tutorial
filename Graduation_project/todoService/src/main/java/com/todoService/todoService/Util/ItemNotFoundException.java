package com.todoService.todoService.Util;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
    public ItemNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public ItemNotFoundException(Throwable cause){
        super(cause);
    }
}
