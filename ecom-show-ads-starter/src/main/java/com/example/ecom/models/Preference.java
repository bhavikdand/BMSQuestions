package com.example.ecom.models;

import lombok.Data;

import java.util.Date;

@Data
public class Preference extends BaseModel{
    private String category;
    private String description;
    private Date createdAt;
}
