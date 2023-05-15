package com.examples.lld.bms.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;

    private Integer rowIndex;
    private Integer columnIndex;

    @ManyToOne
    private SeatType seatType;

}
