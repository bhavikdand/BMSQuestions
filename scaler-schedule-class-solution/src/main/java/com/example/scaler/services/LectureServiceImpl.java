package com.example.scaler.services;

import com.example.scaler.exceptions.InvalidLectureException;
import com.example.scaler.exceptions.UnAuthorizedException;
import com.example.scaler.models.Batch;
import com.example.scaler.models.BatchLearner;
import com.example.scaler.models.LearnerProgress;
import com.example.scaler.models.ScheduledLecture;
import com.example.scaler.repositories.BatchLearnerRepository;
import com.example.scaler.repositories.LearnerProgressRepository;
import com.example.scaler.repositories.LearnerRepository;
import com.example.scaler.repositories.ScheduledLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureServiceImpl implements LectureService{

    private ScheduledLectureRepository scheduledLectureRepository;

    private BatchLearnerRepository batchLearnerRepository;

    private LearnerProgressRepository learnerProgressRepository;

    private LearnerRepository learnerRepository;

    @Autowired
    public LectureServiceImpl(ScheduledLectureRepository scheduledLectureRepository, BatchLearnerRepository batchLearnerRepository, LearnerProgressRepository learnerProgressRepository, LearnerRepository learnerRepository) {
        this.scheduledLectureRepository = scheduledLectureRepository;
        this.batchLearnerRepository = batchLearnerRepository;
        this.learnerProgressRepository = learnerProgressRepository;
        this.learnerRepository = learnerRepository;
    }

    @Override
    public LearnerProgress accessLecture(long learnerId, long scheduledLectureId, String lectureLink) throws InvalidLectureException, UnAuthorizedException {

        this.learnerRepository.findById(learnerId).orElseThrow(()->new UnAuthorizedException("Learner not found"));

        ScheduledLecture scheduledLecture;
        if(lectureLink != null){
            scheduledLecture = scheduledLectureRepository.findByLectureLink(lectureLink).orElseThrow(()->new InvalidLectureException("Lecture not found"));
        } else {
            scheduledLecture = scheduledLectureRepository.findById(scheduledLectureId).orElseThrow(()->new InvalidLectureException("Lecture not found"));
        }

        Batch batch = scheduledLecture.getBatch();
        BatchLearner batchLearner = batchLearnerRepository.findByBatchIdAndLearnerId(batch.getId(), learnerId).orElseThrow(() -> new UnAuthorizedException("Learner not authorized to access this lecture."));

        if(batchLearner.getEntryDate().after(scheduledLecture.getLectureStartTime())){
            throw new UnAuthorizedException("Learner not authorized to access this lecture.");
        }

        if(batchLearner.getExitDate() != null && batchLearner.getExitDate().before(scheduledLecture.getLectureEndTime())){
            throw new UnAuthorizedException("Learner not authorized to access this lecture.");
        }

        return this.learnerProgressRepository.findByLearnerIdAndScheduledLectureId(learnerId, scheduledLectureId).orElseGet(() -> {
            LearnerProgress learnerProgress = new LearnerProgress();
            learnerProgress.setLearner(batchLearner.getLearner());
            learnerProgress.setScheduledLecture(scheduledLecture);
            learnerProgress.setCompletedTimeInSecs(0);
            return this.learnerProgressRepository.save(learnerProgress);
        });
    }
}
