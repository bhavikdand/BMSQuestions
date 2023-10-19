package com.example.bmsbookticket.models;

import lombok.Data;

@Data
public class Movie extends BaseModel{
    private String name;
    private String description;
}
