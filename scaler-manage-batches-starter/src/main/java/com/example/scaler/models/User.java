package com.example.scaler.models;

import lombok.Data;

@Data
public class User extends BaseModel{

    private String name;
    private String email;
    private UserType userType;
}
