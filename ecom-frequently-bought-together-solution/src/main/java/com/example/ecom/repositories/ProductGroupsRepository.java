package com.example.ecom.repositories;

import com.example.ecom.models.Product;
import com.example.ecom.models.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGroupsRepository extends JpaRepository<ProductGroup, Integer> {

    public List<ProductGroup> findByProductsContaining(Product product);
}
