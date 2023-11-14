package com.example.bmsbookticket.models;

import lombok.Data;

@Data
public class Rating extends BaseModel{
    private User user;
    private Movie movie;
    private int rating;
}
