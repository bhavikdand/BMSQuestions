package com.example.bmsbookticket.models;


import lombok.Data;

import java.util.List;

@Data
public class Theatre extends BaseModel{

    private String name;

    private String address;

    private List<Screen> screens;

    private City city;

}
