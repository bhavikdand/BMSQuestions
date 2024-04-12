package com.example.qcommerce.models;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Partner extends BaseModel{
    private String name;
    @Embedded
    private Location currentLocation;
}
