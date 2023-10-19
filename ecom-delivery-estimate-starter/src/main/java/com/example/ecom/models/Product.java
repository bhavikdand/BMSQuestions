package com.example.ecom.models;

import lombok.Data;

@Data
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private Seller seller;
}
