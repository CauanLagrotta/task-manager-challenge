package com.cauanlagrotta.task_manager_challenge.repository;

import com.cauanlagrotta.task_manager_challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  boolean existsByEmail(String email);
}
