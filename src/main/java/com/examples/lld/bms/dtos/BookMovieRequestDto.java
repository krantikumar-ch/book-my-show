package com.examples.lld.bms.dtos;


import java.util.List;

public record BookMovieRequestDto(List<Long> showSeatId, Long userId, Long showId) {

}
