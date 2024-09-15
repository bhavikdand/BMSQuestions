# Implement functionality using which admins can manage batches for Scaler Academy

## Problem Statement

You are building web application for Scaler Academy. As a part of this system, you need to implement a functionality which will be used by Admins to manage batches.

## Requirements

* You will need to build 2 functionalities:
    * Add a learner to a batch
    * Remove a learner from a batch

* Add a learner to a batch
  * This functionality will be used to add a learner to a batch. The request will contain the following information:
    * Batch Id
    * Learner Id
    * User id (of the admin who is adding the learner)
  * If a learner is already present in a batch, then you should not add the learner again. You should return an error message in this case.
  * Only add a learner to a batch if the learner is not already present in any batch.
  * Only users with role `ADMIN` can add a learner to a batch.
  * BatchLearner entity is used to track the learners in a batch. You should create a new entry in this table for every learner added to a batch and return this object in response.

* Remove a learner from a batch
  * This functionality will be used to remove a learner from a batch. The request will contain the following information:
    * Learner Id
    * User id (of the admin who is removing the learner)
  * Only users with role `ADMIN` can remove a learner from a batch.
  * We need to get the current batch for the user and remove the user from that batch.
  * BatchLearner entity is used to track the learners in a batch. You should update the end date of the batch learner entry to the current date and return this object in response.
  * If the learner is not present in any batch, then you should return an error message.
  * If the learner is present in only one batch, then you should remove the learner from that batch.


## Instructions
1. Carefully look at the dto package. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the incomplete methods inside the `BatchController`.
4. Implement the `BatchService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.