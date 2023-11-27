package com.example.qcommerce.controllers;

import com.example.qcommerce.dtos.BuildBatchedTaskRouteRequestDto;
import com.example.qcommerce.dtos.BuildBatchedTaskRouteResponseDto;
import com.example.qcommerce.dtos.ResponseStatus;
import com.example.qcommerce.models.Location;
import com.example.qcommerce.services.BatchedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BatchedTaskController {

    private BatchedTaskService batchedTaskService;

    @Autowired
    public BatchedTaskController(BatchedTaskService batchedTaskService) {
        this.batchedTaskService = batchedTaskService;
    }

    public BuildBatchedTaskRouteResponseDto buildRoute(BuildBatchedTaskRouteRequestDto requestDto){
        BuildBatchedTaskRouteResponseDto responseDto = new BuildBatchedTaskRouteResponseDto();
        try{
            List<Location> routeToBeTaken = batchedTaskService.buildRoute(requestDto.getBatchedTaskId());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setRouteToBeTaken(routeToBeTaken);
        } catch (Exception e){
            e.printStackTrace();
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
