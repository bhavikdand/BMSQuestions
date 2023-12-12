# Implement functionality to allow learners to access lectures for Scaler Academy

## Problem Statement

You are building web application for Scaler Academy. As a part of this system, you need to implement a functionality will allow learners to access lectures.

## Requirements

* Learners can access lectures in 2 ways:
  * By accessing the lecture directly via dashboard. In this case the functionality will get the scheduled lecture id in the request.
  * By clicking on the link in their calendars. In this case the functionality will get the lecture link in the request.
* The request will either contain the scheduled lecture id or the lecture link, not both. ALong with this, the request will also contain the learner id.
* As we know learners can change their batches in between. So a learner can have multiple previous batches and only 1 current batch.
* So we need to make sure that the lecture the learner is trying to access happened in the current batch or in one of the previous batches, if yes then we will allow the learner to access the lecture or else we will return an error.
* Data related to learners and batches will be stored in a table named "learner_batch_mapping" tracked via `LearnerBatch` entity.
* This table has columns like entry_date and exit_date which will tell us when a learner joined and left a batch. A null exit_date means that the student is still in the batch.
* If a learner is allowed to access the lecture, then we should return details about till what time the learner has completed watching a lecture. 
* This data will be stored in a table named "lecture_progress" tracked via `LectureProgress` entity.
* If no entry is found in the lecture_progress table for a learner and a lecture, then we will assume that the learner has not watched any part of the lecture and create a new entry in the table with completed_time as 0.
* In case of correct access, we will return the entry from the lecture_progress table for the learner else we will throw UnAuthorizedAccess exception.

## Instructions
1. Carefully look at the dto package. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `scheduleLectures` method inside the `LectureController`.
4. Implement the `LectureService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.