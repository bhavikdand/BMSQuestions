package com.example.scaler.models;

import java.util.Date;

public class LearnerExam extends BaseModel{

    private Learner learner;

    private Exam exam;

    private Date startedAt;

    private Date endedAt;

    private ExamStatus status;

    private int scoreObtained;
}
