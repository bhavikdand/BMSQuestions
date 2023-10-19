package com.example.ecom.services;

import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Inventory;

public interface InventoryService {
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity) throws ProductNotFoundException, UserNotFoundException;
    public void deleteInventory(int userId, int productId) throws  UserNotFoundException;
}
