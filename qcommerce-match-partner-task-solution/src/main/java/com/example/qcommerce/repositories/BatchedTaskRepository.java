package com.example.qcommerce.repositories;

import com.example.qcommerce.models.BatchedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchedTaskRepository extends JpaRepository<BatchedTask, Long> {
}
