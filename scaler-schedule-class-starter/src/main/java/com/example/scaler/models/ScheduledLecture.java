package com.example.scaler.models;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduledLecture extends BaseModel{

    private Lecture lecture;
    private Batch batch;
    private Instructor instructor;
    private Date lectureStartTime;
    private Date lectureEndTime;
    private String lectureLink;
}
