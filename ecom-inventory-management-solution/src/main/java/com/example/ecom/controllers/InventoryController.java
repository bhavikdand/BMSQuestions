package com.example.ecom.controllers;

import com.example.ecom.dtos.*;
import com.example.ecom.models.Inventory;
import com.example.ecom.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {


    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto){
        CreateOrUpdateResponseDto responseDto = new CreateOrUpdateResponseDto();
        try{
            Inventory inventory = inventoryService.createOrUpdateInventory(requestDto.getUserId(), requestDto.getProductId(), requestDto.getQuantity());
            responseDto.setInventory(inventory);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto){
        DeleteInventoryResponseDto responseDto = new DeleteInventoryResponseDto();
        try{
            inventoryService.deleteInventory(requestDto.getUserId(), requestDto.getProductId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }


}
