package com.example.ecom.controllers;

import com.example.ecom.dtos.CreateOrUpdateRequestDto;
import com.example.ecom.dtos.CreateOrUpdateResponseDto;
import com.example.ecom.dtos.DeleteInventoryRequestDto;
import com.example.ecom.dtos.DeleteInventoryResponseDto;
import com.example.ecom.services.InventoryService;

public class InventoryController {


    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto){
        return null;
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto){
        return null;
    }


}
