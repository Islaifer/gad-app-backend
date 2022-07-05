package com.fatec.gad.exception;

public class InvalidRegisterException extends Exception{

    public InvalidRegisterException (String msg){
        super(msg);
    }

    public InvalidRegisterException(String msg, Throwable cause){
        super(msg, cause);
    }
}
