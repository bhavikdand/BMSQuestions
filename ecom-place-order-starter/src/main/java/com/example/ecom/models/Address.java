package com.example.ecom.models;

import lombok.Data;

@Data
public class Address extends BaseModel{
    private User user;
    private String building;
    private int floor;
    private String roomNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
