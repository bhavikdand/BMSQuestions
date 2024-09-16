package com.example.splitwise.models;

import lombok.Data;

@Data
public class ExpenseUser extends BaseModel{

    private Expense expense;
    private User user;
    private double amount;
    private ExpenseType expenseType;
}
