package com.example.scaler.services;

import com.example.scaler.exceptions.InvalidBatchException;
import com.example.scaler.exceptions.InvalidLearnerException;
import com.example.scaler.exceptions.InvalidUserException;
import com.example.scaler.exceptions.UnAuthorizedException;
import com.example.scaler.models.BatchLearner;

public interface BatchService {

    public BatchLearner addLearnerToBatch(long learnerId, long batchId, long userId) throws InvalidUserException, UnAuthorizedException, InvalidLearnerException, InvalidBatchException;

    public BatchLearner removeLearnerFromBatch(long learnerId, long userId) throws InvalidUserException, UnAuthorizedException, InvalidLearnerException, InvalidBatchException;
}
