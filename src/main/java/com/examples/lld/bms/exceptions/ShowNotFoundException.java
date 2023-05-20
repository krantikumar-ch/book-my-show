package com.examples.lld.bms.exceptions;

import com.examples.lld.bms.models.Show;

public class ShowNotFoundException extends RuntimeException{

    public ShowNotFoundException(){
        super("Show not found");
    }

    public ShowNotFoundException(String message){
        super(message);
    }
}
