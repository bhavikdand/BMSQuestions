package com.example.scaler.dtos;

import lombok.Data;

@Data
public class AddLearnerToBatchRequestDto {
    private long learnerId;
    private long batchId;
    private long userId;
}
