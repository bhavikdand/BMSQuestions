package com.example.qcommerce.services;

import com.example.qcommerce.adapters.MapsAdapter;
import com.example.qcommerce.exceptions.BatchedTaskNotFoundException;
import com.example.qcommerce.models.BatchedTask;
import com.example.qcommerce.models.Location;
import com.example.qcommerce.models.Task;
import com.example.qcommerce.repositories.BatchedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildBatchedTaskServiceImpl implements BuildBatchedTaskService{

    private BatchedTaskRepository batchedTaskRepository;
    private MapsAdapter mapsAdapter;

    @Autowired
    public BuildBatchedTaskServiceImpl(BatchedTaskRepository batchedTaskRepository, MapsAdapter mapsAdapter) {
        this.batchedTaskRepository = batchedTaskRepository;
        this.mapsAdapter = mapsAdapter;
    }

    @Override
    public List<Location> buildRoute(long batchedTaskId) throws BatchedTaskNotFoundException {
        Optional<BatchedTask> batchedTaskOptional = batchedTaskRepository.findById(batchedTaskId);
        if(batchedTaskOptional.isEmpty()){
            throw new BatchedTaskNotFoundException("Batched Task not found");
        }
        BatchedTask batchedTask = batchedTaskOptional.get();
        List<Location> dropLocations = batchedTask.getTasks().stream().map(Task::getDropLocation).toList();
        return mapsAdapter.buildRoute(dropLocations);
    }
}
