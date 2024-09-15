package com.example.scaler.controllers;

import com.example.scaler.dtos.*;
import com.example.scaler.models.*;
import com.example.scaler.repositories.BatchLearnerRepository;
import com.example.scaler.repositories.BatchRepository;
import com.example.scaler.repositories.LearnerRepository;
import com.example.scaler.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestBatchController {
    @Autowired
    private BatchLearnerRepository batchLearnerRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BatchController batchController;

    private User admin,nonAdmin;
    private Learner learner;
    private Batch batch;

    @BeforeEach
    public void setup() {
        admin = new User();
        admin.setName("admin");
        admin.setUserType(UserType.ADMIN);
        admin = userRepository.save(admin);

        nonAdmin = new User();
        nonAdmin.setName("nonAdmin");
        nonAdmin.setUserType(UserType.INSTRUCTOR);
        nonAdmin = userRepository.save(nonAdmin);

        learner = new Learner();
        learner = learnerRepository.save(learner);

        batch = new Batch();
        batch = batchRepository.save(batch);
    }


    @Test
    public void testAddLearnerToBatch_UserNotFound() {
        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(batch.getId());
        addLearnerToBatchRequestDto.setLearnerId(learner.getId());
        addLearnerToBatchRequestDto.setUserId(100L);

        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testAddLearnerToBatch_UserNotAdmin() {
        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(batch.getId());
        addLearnerToBatchRequestDto.setLearnerId(learner.getId());
        addLearnerToBatchRequestDto.setUserId(nonAdmin.getId());

        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testAddLearnerToBatch_LearnerNotFound() {
        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(batch.getId());
        addLearnerToBatchRequestDto.setLearnerId(100L);
        addLearnerToBatchRequestDto.setUserId(admin.getId());

        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testAddLearnerToBatch_BatchNotFound() {
        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(100L);
        addLearnerToBatchRequestDto.setLearnerId(learner.getId());
        addLearnerToBatchRequestDto.setUserId(admin.getId());

        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testAddLearnerToBatch_LearnerAlreadyAddedToBatch() {

        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner);
        batchLearner.setStartDate(new Date());
        batchLearnerRepository.save(batchLearner);

        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(batch.getId());
        addLearnerToBatchRequestDto.setLearnerId(learner.getId());
        addLearnerToBatchRequestDto.setUserId(admin.getId());


        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testAddLearnerToBatch_Success() {
        AddLearnerToBatchRequestDto addLearnerToBatchRequestDto = new AddLearnerToBatchRequestDto();
        addLearnerToBatchRequestDto.setBatchId(batch.getId());
        addLearnerToBatchRequestDto.setLearnerId(learner.getId());
        addLearnerToBatchRequestDto.setUserId(admin.getId());

        AddLearnerToBatchResponseDto responseDto = batchController.addLearnerToBatch(addLearnerToBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be success");
        assertNotNull(responseDto.getBatchLearner(), "Batch learner obj in response should not be null");
        assertEquals(batch.getId(), responseDto.getBatchLearner().getBatch().getId(), "Batch learner batch id should match");
        assertEquals(learner.getId(), responseDto.getBatchLearner().getLearner().getId(), "Batch learner learner id should match");
        assertNull(responseDto.getBatchLearner().getEndDate(), "Batch learner end date should be null");
    }

    @Test
    public void testRemoveLearnerFromBatch_UserNotFound() {
        RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto = new RemoveLearnerFromBatchRequestDto();
        removeLearnerFromBatchRequestDto.setLearnerId(learner.getId());
        removeLearnerFromBatchRequestDto.setUserId(100L);

        RemoveLearnerFromBatchResponseDto responseDto = batchController.removeLearnerFromBatch(removeLearnerFromBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testRemoveLearnerFromBatch_UserNotAdmin() {
        RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto = new RemoveLearnerFromBatchRequestDto();
        removeLearnerFromBatchRequestDto.setLearnerId(learner.getId());
        removeLearnerFromBatchRequestDto.setUserId(nonAdmin.getId());

        RemoveLearnerFromBatchResponseDto responseDto = batchController.removeLearnerFromBatch(removeLearnerFromBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testRemoveLearnerFromBatch_LearnerNotFound() {
        RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto = new RemoveLearnerFromBatchRequestDto();
        removeLearnerFromBatchRequestDto.setLearnerId(100L);
        removeLearnerFromBatchRequestDto.setUserId(admin.getId());

        RemoveLearnerFromBatchResponseDto responseDto = batchController.removeLearnerFromBatch(removeLearnerFromBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testRemoveLearnerFromBatch_LearnerNotPresentInAnyBatch() {
        RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto = new RemoveLearnerFromBatchRequestDto();
        removeLearnerFromBatchRequestDto.setLearnerId(learner.getId());
        removeLearnerFromBatchRequestDto.setUserId(admin.getId());

        RemoveLearnerFromBatchResponseDto responseDto = batchController.removeLearnerFromBatch(removeLearnerFromBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }

    @Test
    public void testRemoveLearnerFromBatch_Success() {
        BatchLearner batchLearner = new BatchLearner();
        batchLearner.setBatch(batch);
        batchLearner.setLearner(learner);
        batchLearner.setStartDate(new Date());
        batchLearnerRepository.save(batchLearner);

        RemoveLearnerFromBatchRequestDto removeLearnerFromBatchRequestDto = new RemoveLearnerFromBatchRequestDto();
        removeLearnerFromBatchRequestDto.setLearnerId(learner.getId());
        removeLearnerFromBatchRequestDto.setUserId(admin.getId());

        RemoveLearnerFromBatchResponseDto responseDto = batchController.removeLearnerFromBatch(removeLearnerFromBatchRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be success");
        assertNotNull(responseDto.getBatchLearner(), "Batch learner obj in response should not be null");
        assertEquals(batch.getId(), responseDto.getBatchLearner().getBatch().getId(), "Batch learner batch id should match");
        assertEquals(learner.getId(), responseDto.getBatchLearner().getLearner().getId(), "Batch learner learner id should match");
        assertNotNull(responseDto.getBatchLearner().getEndDate(), "Batch learner end date should not be null");
    }


}
