package com.example.ecom.models;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private int quantity;
}
