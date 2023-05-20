package com.examples.lld.bms.exceptions;

public class PasswordMismatchException extends RuntimeException{

    public PasswordMismatchException(){
        super("Password not matched");
    }

    public PasswordMismatchException(String message){
        super(message);
    }
}
