package com.examples.lld.bms.dtos;


public record ResponseDto<T>(ResponseStatus responseStatus, String message, T body) {

    public ResponseDto(ResponseStatus responseStatus, String message){
        this(responseStatus, message, null);
    }

    public ResponseDto(ResponseStatus responseStatus, T body) {
        this(responseStatus, null, body);
    }
}
