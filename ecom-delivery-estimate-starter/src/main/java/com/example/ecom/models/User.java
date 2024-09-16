package com.example.ecom.models;

import lombok.Data;

import java.util.List;

@Data
public class User extends BaseModel{
    private String name;
    private String email;
    private List<Address> addresses;
}
