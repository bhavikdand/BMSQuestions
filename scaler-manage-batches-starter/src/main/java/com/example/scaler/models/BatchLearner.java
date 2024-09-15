package com.example.scaler.models;

import lombok.Data;

import java.util.Date;

@Data
public class BatchLearner extends BaseModel{
    private Batch batch;
    private Learner learner;
    private Date startDate;
    private Date endDate;
}


