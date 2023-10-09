package com.example.ecom.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Show extends BaseModel{
    private Movie movie;
    private Date startTime;
    private Date endTime;

    private List<Feature> features;
    private Screen screen;
}
