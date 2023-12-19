package com.example.scaler.dtos;

import com.example.scaler.models.BatchLearner;
import lombok.Data;

@Data
public class RemoveLearnerFromBatchResponseDto {

    private BatchLearner batchLearner;
    private ResponseStatus responseStatus;
}
