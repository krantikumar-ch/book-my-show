package com.examples.lld.bms.exceptions;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(){
        super("Invalid Email Id");
    }

    public InvalidEmailException(String message){
        super(message);
    }
}
