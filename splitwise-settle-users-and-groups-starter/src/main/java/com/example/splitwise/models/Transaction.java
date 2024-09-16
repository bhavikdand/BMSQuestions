package com.example.splitwise.models;

import lombok.Data;

@Data
public class Transaction extends BaseModel{
    private User paidFrom;
    private User paidTo;
    private double amount;
}
