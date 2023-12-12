package com.example.scaler.services;

import com.example.scaler.exceptions.InvalidLectureException;
import com.example.scaler.exceptions.UnAuthorizedException;
import com.example.scaler.models.LearnerProgress;

public interface LectureService {

    LearnerProgress accessLecture(long learnerId, long scheduledLectureId, String lectureLink) throws InvalidLectureException, UnAuthorizedException;
}
