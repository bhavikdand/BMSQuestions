package com.example.ecom.models;

import lombok.Data;

@Data
public class DeliveryHub extends BaseModel{
    private Address address;
    private String name;
}
