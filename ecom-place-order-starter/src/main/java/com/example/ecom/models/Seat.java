package com.example.ecom.models;

import lombok.Data;

@Data
public class Seat extends BaseModel{
    private String name;
    SeatType seatType;
    Screen screen;
}
