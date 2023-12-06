package com.example.ecom.models;

import lombok.Data;

@Data
public class Advertisement extends BaseModel{
    private String name;
    private String description;
    private Preference preference;
}
