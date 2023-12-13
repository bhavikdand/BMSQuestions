package com.example.scaler.models;

import java.util.List;

public class Question extends BaseModel{
    private String name;
    private String description;
    private Exam exam;
    private List<Option> options;
    private Option correctOption;
    private int score;
}
