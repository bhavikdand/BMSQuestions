package com.example.ecom.controllers;

import com.example.ecom.dtos.UpdateInventoryRequestDto;
import com.example.ecom.dtos.UpdateInventoryResponseDto;
import com.example.ecom.services.InventoryService;

public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public UpdateInventoryResponseDto updateInventory(UpdateInventoryRequestDto requestDto) {
        return null;
    }
}
