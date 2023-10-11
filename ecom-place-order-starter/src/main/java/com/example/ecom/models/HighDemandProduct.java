package com.example.ecom.models;

import lombok.Data;

@Data
public class HighDemandProduct extends BaseModel{
    private Product product;
    private int maxQuantity;
}
