package com.example.ecom.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GiftCard extends BaseModel{
    private double amount;
    private Date createdAt;
    private Date expiresAt;

    private List<LedgerEntry> ledger;
    private String giftCardCode;
}
