package com.example.ecom.models;

import lombok.Data;

@Data
public class Notification extends BaseModel{
    private Product product;
    private User user;
    private NotificationStatus status;
}
