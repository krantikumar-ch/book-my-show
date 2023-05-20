package com.examples.lld.bms.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookMovieResponseDto extends BaseResponseDto{
    private Integer amount;
    private Long bookingId;
}
