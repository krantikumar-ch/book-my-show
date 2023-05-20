package com.examples.lld.bms.exceptions;

public class EmailExistsException extends RuntimeException{

    public EmailExistsException(){
        super("Email Already exists. Try with another Email");
    }

    public EmailExistsException(String message){
        super(message);
    }
}
