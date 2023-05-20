package com.examples.lld.bms.exceptions;

public class SeatsUnavailableException extends RuntimeException{

    public SeatsUnavailableException(){
        super("Seats are not available");
    }

    public SeatsUnavailableException(String message){
        super(message);
    }
}
