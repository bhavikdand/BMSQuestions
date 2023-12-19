package com.example.scaler.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BatchLearner extends BaseModel{
    @ManyToOne
    private Batch batch;
    @ManyToOne
    private Learner learner;
    private Date startDate;
    private Date endDate;
}


