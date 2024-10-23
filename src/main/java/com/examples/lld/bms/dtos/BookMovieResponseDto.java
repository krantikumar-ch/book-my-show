package com.examples.lld.bms.dtos;


public record BookMovieResponseDto(Integer amount, Long bookingId, ResponseStatus status, String message) implements BaseResponseRecord{

    public BookMovieResponseDto( Long bookingId, Integer amount, ResponseStatus status){
        this(amount, bookingId, status, null);
    }

    public BookMovieResponseDto(ResponseStatus status, String message){
        this(null, null, status, message);
    }
}
