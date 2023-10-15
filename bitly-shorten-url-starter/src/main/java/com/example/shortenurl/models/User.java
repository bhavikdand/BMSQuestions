package com.example.shortenurl.models;

import lombok.Data;

@Data
public class User extends BaseModel{
    private String name;
    private String email;
    private UserPlan userPlan;
}
