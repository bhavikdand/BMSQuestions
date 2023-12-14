package com.example.splitwise.models;

import lombok.Data;

@Data
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String password;
}
