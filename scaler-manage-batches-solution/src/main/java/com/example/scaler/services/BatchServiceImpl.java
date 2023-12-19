package com.example.scaler.services;

import com.example.scaler.exceptions.InvalidBatchException;
import com.example.scaler.exceptions.InvalidLearnerException;
import com.example.scaler.exceptions.InvalidUserException;
import com.example.scaler.exceptions.UnAuthorizedException;
import com.example.scaler.models.*;
import com.example.scaler.repositories.BatchLearnerRepository;
import com.example.scaler.repositories.BatchRepository;
import com.example.scaler.repositories.LearnerRepository;
import com.example.scaler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BatchServiceImpl implements BatchService{

    private UserRepository userRepository;
    private BatchLearnerRepository batchLearnerRepository;
    private BatchRepository batchRepository;
    private LearnerRepository learnerRepository;

    @Autowired
    public BatchServiceImpl(UserRepository userRepository, BatchLearnerRepository batchLearnerRepository, BatchRepository batchRepository, LearnerRepository learnerRepository) {
        this.userRepository = userRepository;
        this.batchLearnerRepository = batchLearnerRepository;
        this.batchRepository = batchRepository;
        this.learnerRepository = learnerRepository;
    }

    @Override
    public BatchLearner addLearnerToBatch(long learnerId, long batchId, long userId) throws InvalidUserException, UnAuthorizedException, InvalidLearnerException, InvalidBatchException {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User not found"));
        if(user.getUserType() != UserType.ADMIN){
            throw new UnAuthorizedException("User is not an admin");
        }

        Learner learner = this.learnerRepository.findById(learnerId).orElseThrow(() -> new InvalidLearnerException("Learner not found"));

        Batch batch = this.batchRepository.findById(batchId).orElseThrow(() -> new InvalidBatchException("Batch not found"));

        BatchLearner exisitingBatchLearner = this.batchLearnerRepository.findByBatchIdAndLearnerIdAndEndDateIsNull(batchId, learnerId).orElse(null);

        if(exisitingBatchLearner != null){
            throw new InvalidBatchException("Learner already added to batch");
        }

        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner);
        batchLearner.setStartDate(new Date());
        return this.batchLearnerRepository.save(batchLearner);
    }

    @Override
    public BatchLearner removeLearnerFromBatch(long learnerId, long userId) throws InvalidUserException, UnAuthorizedException, InvalidLearnerException, InvalidBatchException {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User not found"));
        if(user.getUserType() != UserType.ADMIN){
            throw new UnAuthorizedException("User is not an admin");
        }
        this.learnerRepository.findById(learnerId).orElseThrow(() -> new InvalidLearnerException("Learner not found"));
        BatchLearner existingBatch = this.batchLearnerRepository.findByBatchIdAndLearnerIdAndEndDateIsNull(learnerId, learnerId).orElse(null);

        if(existingBatch == null){
            throw new InvalidBatchException("Learner not present in any batch");
        }
        existingBatch.setEndDate(new Date());
        return this.batchLearnerRepository.save(existingBatch);
    }
}
