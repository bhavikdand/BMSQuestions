package com.example.scaler.repositories;

import com.example.scaler.models.BatchLearner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchLearnerRepository extends JpaRepository<BatchLearner, Long> {

    Optional<BatchLearner> findByBatchIdAndLearnerId(Long batchId, Long learnerId);
}
