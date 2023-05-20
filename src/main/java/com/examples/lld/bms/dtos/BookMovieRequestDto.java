package com.examples.lld.bms.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    List<Long> showSeatId;
    private Long userId;
    private Long showId;
}
