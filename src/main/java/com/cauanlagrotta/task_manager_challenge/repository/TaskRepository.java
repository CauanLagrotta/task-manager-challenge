package com.cauanlagrotta.task_manager_challenge.repository;

import com.cauanlagrotta.task_manager_challenge.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  @Query("SELECT t FROM Task t WHERE t.userId.id = :userId")
  Page<Task> findAllByUserId(@Param("userId") UUID userId, PageRequest pageRequest);
}
