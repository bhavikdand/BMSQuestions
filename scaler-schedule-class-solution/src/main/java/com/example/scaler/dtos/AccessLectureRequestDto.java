package com.example.scaler.dtos;

import lombok.Data;

@Data
public class AccessLectureRequestDto {
    private long learnerId;
    private long scheduledLectureId;
    private String lectureLink;
}
