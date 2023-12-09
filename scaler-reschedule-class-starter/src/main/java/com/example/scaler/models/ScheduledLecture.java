package com.example.scaler.models;

import java.util.Date;

public class ScheduledLecture extends BaseModel{

    private Lecture lecture;
    private Batch batch;
    private Instructor instructor;
    private Date lectureStartTime;
    private Date lectureEndTime;
    private String lectureLink;
}
