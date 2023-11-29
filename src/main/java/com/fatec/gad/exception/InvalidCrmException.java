package com.fatec.gad.exception;

public class InvalidCrmException extends Exception{
    public InvalidCrmException (String msg){
        super(msg);
    }

    public InvalidCrmException(String msg, Throwable cause){
        super(msg, cause);
    }
}
