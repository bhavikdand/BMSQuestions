package com.example.scaler.models;

import lombok.Data;

@Data
public class Batch extends BaseModel{

    private String name;
    private Schedule schedule;

}
