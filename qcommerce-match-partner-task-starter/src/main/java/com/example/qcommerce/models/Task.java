package com.example.qcommerce.models;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Task extends BaseModel{
    private long customerId;
    @Embedded
    private Location pickupLocation;
}
