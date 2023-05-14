package com.examples.lld.bms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Movie extends BaseModel{
    private String name;
}
