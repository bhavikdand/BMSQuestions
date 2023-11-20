package com.example.qcommerce.repositories;

import com.example.qcommerce.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
