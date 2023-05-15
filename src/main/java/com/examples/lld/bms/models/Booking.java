package com.examples.lld.bms.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingstatus;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    private Date bookedAt;

    @ManyToOne
    private Show show;

    private Integer amount;

    @OneToMany
    private List<Payment> payment;
}
