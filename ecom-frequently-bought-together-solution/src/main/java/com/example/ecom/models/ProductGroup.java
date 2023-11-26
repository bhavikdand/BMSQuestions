package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Entity
public class ProductGroup extends BaseModel{
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
