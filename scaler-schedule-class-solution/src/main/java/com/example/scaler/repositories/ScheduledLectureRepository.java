package com.example.scaler.repositories;

import com.example.scaler.models.ScheduledLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduledLectureRepository extends JpaRepository<ScheduledLecture, Long> {

    Optional<ScheduledLecture> findByLectureLink(String lectureLink);
}
