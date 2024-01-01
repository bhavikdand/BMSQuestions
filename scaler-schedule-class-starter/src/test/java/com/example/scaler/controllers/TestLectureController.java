package com.example.scaler.controllers;

import com.example.scaler.dtos.AccessLectureRequestDto;
import com.example.scaler.dtos.AccessLectureResponseDto;
import com.example.scaler.dtos.ResponseStatus;
import com.example.scaler.models.*;
import com.example.scaler.repositories.BatchRepository;
import com.example.scaler.repositories.InstructorRepository;
import com.example.scaler.repositories.LectureRepository;
import com.example.scaler.repositories.ScheduledLectureRepository;
import com.example.scaler.utils.DronaUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestLectureController {

    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private ScheduledLectureRepository scheduledLectureRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private LearnerProgressRepository learnerProgressRepository;
    @Autowired
    private BatchLearnerRepository batchLearnerRepository;

    @Autowired
    private LectureController lectureController;

    private List<Long> lectureIds = new ArrayList<>();
    private Batch batch;
    private Learner learner;
    private Instructor instructor;
    private ScheduledLecture scheduledLecture1, scheduledLecture2;

    @BeforeEach
    public void setup(){
        batch = new Batch();
        batch.setName("Batch 1");
        batch.setSchedule(Schedule.MWF_MORNING);
        batch = batchRepository.save(batch);

        learner = new Learner();
        learner.setName("Learner 1");
        learner = learnerRepository.save(learner);

        instructor = new Instructor();
        instructor.setName("Instructor 1");
        instructor = instructorRepository.save(instructor);

        Lecture lecture_1 = new Lecture();
        lecture_1.setName("SQL: Schema Design - 1");
        lecture_1.setDescription("SQL: Schema Design - 1");
        lecture_1 = lectureRepository.save(lecture_1);

        Lecture lecture_0 = new Lecture();
        lecture_0.setName("SQL: Schema Design - 2");
        lecture_0.setDescription("SQL: Schema Design - 2");
        lecture_0 = lectureRepository.save(lecture_0);

        Lecture lecture1 = new Lecture();
        lecture1.setName("Backend LLD: OOP-1: Intro to OOP");
        lecture1.setDescription("Backend LLD: OOP-1: Intro to OOP");
        lecture1 = lectureRepository.save(lecture1);
        lectureIds.add(lecture1.getId());

        Lecture lecture2 = new Lecture();
        lecture2.setName("Backend LLD: OOP-2: Access Modifiers and Constructors");
        lecture2.setDescription("Backend LLD: OOP-2: Access Modifiers and Constructors");
        lecture2 = lectureRepository.save(lecture2);
        lectureIds.add(lecture2.getId());

        Lecture lecture3 = new Lecture();
        lecture3.setName("Backend LLD: OOP-3: Inheritance");
        lecture3.setDescription("Backend LLD: OOP-3: Inheritance");
        lecture3 = lectureRepository.save(lecture3);
        lectureIds.add(lecture3.getId());

        Lecture lecture4 = new Lecture();
        lecture4.setName("Backend LLD: OOP-4: Polymorphism");
        lecture4.setDescription("Backend LLD: OOP-4: Polymorphism");
        lecture4 = lectureRepository.save(lecture4);
        lectureIds.add(lecture4.getId());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 6);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.MINUTE, 30);
        Date endDate = calendar.getTime();

        scheduledLecture1 = new ScheduledLecture();
        scheduledLecture1.setBatch(this.batch);
        scheduledLecture1.setInstructor(instructor);
        scheduledLecture1.setLecture(lecture_1);
        scheduledLecture1.setLectureLink(DronaUtils.generateUniqueLectureLink());
        scheduledLecture1.setLectureStartTime(startDate);
        scheduledLecture1.setLectureEndTime(endDate);
        scheduledLecture1 = scheduledLectureRepository.save(scheduledLecture1);

        calendar.add(Calendar.DATE, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        startDate = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.MINUTE, 30);
        endDate = calendar.getTime();

        scheduledLecture2 = new ScheduledLecture();
        scheduledLecture2.setBatch(this.batch);
        scheduledLecture2.setInstructor(instructor);
        scheduledLecture2.setLecture(lecture_0);
        scheduledLecture2.setLectureLink(DronaUtils.generateUniqueLectureLink());
        scheduledLecture2.setLectureStartTime(startDate);
        scheduledLecture2.setLectureEndTime(endDate);
        scheduledLecture2 = scheduledLectureRepository.save(scheduledLecture2);
    }

    @Test
    public void testAccessLecture_LearnerNotFound(){
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(100);
        requestDto.setScheduledLectureId(1);
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_LectureNotFound_ViaId(){
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner.getId());
        requestDto.setScheduledLectureId(100);
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_LectureNotFound_ViaLink(){
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner.getId());
        requestDto.setLectureLink("https://www.youtube.com/watch?v=1");
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_LearnerNotAuthorized_LearnerNotInBatch(){
        Learner learner1 = new Learner();
        learner1.setName("Learner 2");
        learner1 = learnerRepository.save(learner1);
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner1.getId());
        requestDto.setScheduledLectureId(scheduledLecture1.getId());
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_LearnerNotAuthorized_LearnerInBatchButJoinedAfterTheLectureHappened(){
        Learner learner1 = new Learner();
        learner1.setName("Learner 2");
        learner1 = learnerRepository.save(learner1);
        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner1);
        batchLearner.setEntryDate(new Date());
        batchLearnerRepository.save(batchLearner);
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner1.getId());
        requestDto.setScheduledLectureId(scheduledLecture1.getId());
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_LearnerNotAuthorized_LearnerInBatchButLeftBeforeTheLectureTheLectureHappened(){
        Learner learner1 = new Learner();
        learner1.setName("Learner 2");
        learner1 = learnerRepository.save(learner1);
        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner1);
        batchLearner.setEntryDate(new Date(1698841910));
        batchLearner.setExitDate(new Date(1701347510));
        batchLearnerRepository.save(batchLearner);
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner1.getId());
        requestDto.setScheduledLectureId(scheduledLecture1.getId());
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testAccessLecture_Success(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 6);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();


        Learner learner1 = new Learner();
        learner1.setName("Learner 2");
        learner1 = learnerRepository.save(learner1);
        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner1);
        batchLearner.setEntryDate(startDate);
        batchLearnerRepository.save(batchLearner);
        AccessLectureRequestDto requestDto = new AccessLectureRequestDto();
        requestDto.setLearnerId(learner1.getId());
        requestDto.setScheduledLectureId(scheduledLecture1.getId());
        requestDto.setLectureLink(null);
        AccessLectureResponseDto responseDto = lectureController.accessLecture(requestDto);
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus());
        assertEquals(0, responseDto.getLearnerProgress().getCompletedTimeInSecs());
    }

}
