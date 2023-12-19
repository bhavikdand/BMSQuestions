package com.example.scaler.dtos;

import lombok.Data;

@Data
public class RemoveLearnerFromBatchRequestDto {
    private Long userId;
    private Long learnerId;
}
