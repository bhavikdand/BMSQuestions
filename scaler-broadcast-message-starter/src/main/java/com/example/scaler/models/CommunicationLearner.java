package com.example.scaler.models;

import lombok.Data;

@Data
public class CommunicationLearner extends BaseModel{
    public Learner learner;
    private Communication communication;
    private boolean whatsappDelivered;
    private boolean emailDelivered;
}
