package com.examples.lld.bms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String password;
    private String email;

    @OneToMany
    private List<Booking> bookings;
}
