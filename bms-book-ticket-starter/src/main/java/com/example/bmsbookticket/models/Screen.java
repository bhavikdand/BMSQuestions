package com.example.bmsbookticket.models;

import lombok.Data;

import java.util.List;

@Data
public class Screen extends BaseModel{
    private String name;
    private List<Seat> seats;

    private ScreenStatus status;
    private List<Feature> features;

    private Theatre theatre;
}
