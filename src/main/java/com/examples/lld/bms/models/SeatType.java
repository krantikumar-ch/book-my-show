package com.examples.lld.bms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
@Entity
public class SeatType extends BaseModel{
    private String name;
}
