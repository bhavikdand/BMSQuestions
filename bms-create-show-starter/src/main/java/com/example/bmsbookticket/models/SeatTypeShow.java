package com.example.bmsbookticket.models;

import lombok.Data;

@Data
public class SeatTypeShow extends BaseModel{
    private Show show;

    private SeatType seatType;
    private double price;
}
