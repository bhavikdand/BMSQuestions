package com.example.ecom.models;

import lombok.Data;

@Data
public class Seller extends BaseModel{
    private String name;
    private String email;
    private Address address;
}
