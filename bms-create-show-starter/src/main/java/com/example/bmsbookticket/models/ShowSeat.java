package com.example.bmsbookticket.models;

import lombok.Data;

@Data
public class ShowSeat extends BaseModel{
    private Show show;
    private Seat seat;
    private SeatStatus status;
}
