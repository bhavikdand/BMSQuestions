package com.example.scaler.dtos;

import com.example.scaler.models.BatchLearner;
import lombok.Data;

@Data
public class AddLearnerToBatchResponseDto {

    private BatchLearner batchLearner;
    private ResponseStatus responseStatus;

}
