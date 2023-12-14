package com.example.scaler.dtos;

import com.example.scaler.models.ScheduledLecture;

import java.util.List;

public class FetchTimelineResponseDto {

    private List<ScheduledLecture> lectures;
    private ResponseStatus responseStatus;
}
