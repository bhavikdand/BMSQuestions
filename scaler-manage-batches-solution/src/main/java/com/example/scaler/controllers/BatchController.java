package com.example.scaler.controllers;

import com.example.scaler.dtos.*;
import com.example.scaler.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BatchController {

    private BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    public AddLearnerToBatchResponseDto addLearnerToBatch(AddLearnerToBatchRequestDto addLearnerToBatchRequestDto) {
        AddLearnerToBatchResponseDto responseDto = new AddLearnerToBatchResponseDto();
        try{
            responseDto.setBatchLearner(batchService.addLearnerToBatch(addLearnerToBatchRequestDto.getLearnerId(), addLearnerToBatchRequestDto.getBatchId(), addLearnerToBatchRequestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }

    public RemoveLearnerFromBatchResponseDto removeLearnerFromBatch(RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto) {
        RemoveLearnerFromBatchResponseDto responseDto = new RemoveLearnerFromBatchResponseDto();
        try{
            responseDto.setBatchLearner(batchService.removeLearnerFromBatch(removeLearnerFromBatchRequestDto.getLearnerId(), removeLearnerFromBatchRequestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
