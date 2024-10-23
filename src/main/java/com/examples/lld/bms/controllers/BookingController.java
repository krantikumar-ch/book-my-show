package com.examples.lld.bms.controllers;

import com.examples.lld.bms.dtos.BookMovieRequestDto;
import com.examples.lld.bms.dtos.BookMovieResponseDto;
import com.examples.lld.bms.dtos.ResponseStatus;
import com.examples.lld.bms.models.Booking;
import com.examples.lld.bms.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){
        Long userId = bookMovieRequestDto.userId();
        List<Long> seatIds = bookMovieRequestDto.showSeatId();
        Long showId  = bookMovieRequestDto.showId();
        try{
            Booking booking = bookingService.bookMovie(userId, seatIds, showId);
            return new BookMovieResponseDto(booking.getId(), booking.getAmount(), ResponseStatus.SUCCESS);

        }
        catch(Exception e){
            return new BookMovieResponseDto(ResponseStatus.FAILURE, e.getCause().getMessage());
        }

    }
}
