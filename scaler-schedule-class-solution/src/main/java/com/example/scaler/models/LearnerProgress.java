package com.example.scaler.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class LearnerProgress extends BaseModel{
    @ManyToOne
    private ScheduledLecture scheduledLecture;
    @ManyToOne
    private Learner learner;
    private int completedTimeInSecs;
}
