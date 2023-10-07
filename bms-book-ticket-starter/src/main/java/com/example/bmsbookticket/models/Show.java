package com.example.bmsbookticket.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Show extends BaseModel{
    private Date startTime;
    private Date endTime;

    private List<Feature> features;
    private Screen screen;
}
