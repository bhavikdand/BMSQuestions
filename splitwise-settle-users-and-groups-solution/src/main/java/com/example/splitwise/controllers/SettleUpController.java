package com.example.splitwise.controllers;


import com.example.splitwise.dtos.ResponseStatus;
import com.example.splitwise.dtos.SettleGroupRequestDto;
import com.example.splitwise.dtos.SettleGroupResponseDto;
import com.example.splitwise.dtos.SettleUserRequestDto;
import com.example.splitwise.models.Transaction;
import com.example.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    public SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto dto){
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        try {
            List<Transaction> transactions = settleUpService.settleGroup(dto.getGroupId());
            responseDto.setTransactions(transactions);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public SettleGroupResponseDto settleUser(SettleUserRequestDto requestDto){
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        try{
            List<Transaction> transactions = settleUpService.settleUser(requestDto.getUserId());
            responseDto.setTransactions(transactions);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
