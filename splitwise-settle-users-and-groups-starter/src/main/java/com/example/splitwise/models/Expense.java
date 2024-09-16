package com.example.splitwise.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Expense extends BaseModel{
    private double amount;
    private Date addedAt;
    private String description;
    private String proofUrl;
    private List<ExpenseUser> expenseUsers;
}
