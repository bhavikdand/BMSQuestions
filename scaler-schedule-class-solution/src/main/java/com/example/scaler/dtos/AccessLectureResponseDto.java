package com.example.scaler.dtos;

import com.example.scaler.models.LearnerProgress;
import lombok.Data;

@Data
public class AccessLectureResponseDto {
    private LearnerProgress learnerProgress;
    private ResponseStatus responseStatus;
}
