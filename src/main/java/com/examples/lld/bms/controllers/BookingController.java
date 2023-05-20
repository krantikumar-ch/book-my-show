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
        Long userId = bookMovieRequestDto.getUserId();
        List<Long> seatIds = bookMovieRequestDto.getShowSeatId();
        Long showId  = bookMovieRequestDto.getShowId();
        BookMovieResponseDto response = new BookMovieResponseDto();
        try{
            Booking booking = bookingService.bookMovie(userId, seatIds, showId);
            response.setBookingId(booking.getId());
            response.setAmount(booking.getAmount());
            response.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setExceptionMessage(e.getMessage());
        }

        return response;
    }
}
