package com.example.ecom.services;

import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.models.Product;

import java.util.List;

public interface RecommendationsService {

    public List<Product> getRecommendations(int productId) throws ProductNotFoundException;
}
