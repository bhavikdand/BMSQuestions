package com.example.bmsbookticket.models;


import lombok.Data;


import java.util.List;

@Data
public class City extends BaseModel {
    private String name;
    private List<Theatre> theatres;
}
