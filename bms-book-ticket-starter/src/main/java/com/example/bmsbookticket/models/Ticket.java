package com.example.bmsbookticket.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Ticket extends BaseModel{

    private Show show;

    List<Seat> seats;
    private Date timeOfBooking;

    private User user;

    private TicketStatus status;
}
