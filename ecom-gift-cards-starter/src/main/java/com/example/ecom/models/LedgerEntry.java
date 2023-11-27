package com.example.ecom.models;

import lombok.Data;

import java.util.Date;

@Data
public class LedgerEntry extends BaseModel{

    private TransactionType transactionType;
    private double amount;
    private Date createdAt;
}
