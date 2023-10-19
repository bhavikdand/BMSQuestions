package com.example.ecom.models;

import lombok.Data;

@Data
public class Inventory extends BaseModel{
    private Product product;
    private int quantity;
}
