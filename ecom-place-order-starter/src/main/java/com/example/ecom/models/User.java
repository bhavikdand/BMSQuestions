package com.example.ecom.models;

import lombok.Data;

@Data
public class User extends BaseModel{

    private String name;
    private String email;
    private String password;
    private UserType userType;
}
