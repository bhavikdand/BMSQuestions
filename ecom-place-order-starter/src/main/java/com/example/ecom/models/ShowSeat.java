package com.example.ecom.models;

import lombok.Data;

@Data
public class ShowSeat extends BaseModel{
    private Show show;
    private Seat seat;
    private SeatStatus status;
}
