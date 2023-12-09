package com.example.scaler.models;

import jakarta.persistence.Enumerated;

public class Batch extends BaseModel{

    private String name;
    @Enumerated
    private Schedule schedule;

}
