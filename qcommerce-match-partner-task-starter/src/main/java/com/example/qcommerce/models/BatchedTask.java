package com.example.qcommerce.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BatchedTask extends BaseModel {

    private List<Task> tasks;
    private Date batchedAt;
}
