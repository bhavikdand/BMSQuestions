package com.example.scaler.repositories;

import com.example.scaler.models.LearnerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearnerProgressRepository extends JpaRepository<LearnerProgress, Long> {

    Optional<LearnerProgress> findByLearnerIdAndScheduledLectureId(Long learnerId, Long scheduledLectureId);
}
