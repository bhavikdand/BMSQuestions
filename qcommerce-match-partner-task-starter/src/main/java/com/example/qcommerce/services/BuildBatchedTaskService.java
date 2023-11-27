package com.example.qcommerce.services;

import com.example.qcommerce.exceptions.BatchedTaskNotFoundException;
import com.example.qcommerce.models.Location;

import java.util.List;

public interface BuildBatchedTaskService {

    public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException;
}
