package com.example.ecom.models;

import lombok.Data;

import java.util.List;

@Data
public class Screen extends BaseModel{
    private String name;
    private List<Seat> seats;

    private ScreenStatus status;
    private List<Feature> features;
}
