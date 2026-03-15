# Plan to Fix 500 Internal Server Error

## Problem
The endpoint `GET /api/users/{userId}/tasks` is returning a 500 Internal Server Error after fixing the controller annotation.

## Root Cause
The repository method `findAllByUserId(UUID userId)` is ambiguous when the field `userId` in the `Task` entity is of type `User`. Spring Data JPA may not correctly interpret this query, leading to an exception.

## Solution
Add a `@Query` annotation to make the repository query explicit.

## Steps

### 1. Edit TaskRepository.java
File: `/home/c2000/projects/task-manager-challenge/src/main/java/com/cauanlagrotta/task_manager_challenge/repository/TaskRepository.java`

**Current code:**
```java
package com.cauanlagrotta.task_manager_challenge.repository;

import com.cauanlagrotta.task_manager_challenge.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  Page<Task> findAllByUserId(UUID userId, PageRequest pageRequest);
}
```

**Required changes:**
1. Add imports:
   ```java
   import org.springframework.data.jpa.repository.Query;
   import org.springframework.data.repository.query.Param;
   ```
2. Update the method:
   ```java
   @Query("SELECT t FROM Task t WHERE t.userId.id = :userId")
   Page<Task> findAllByUserId(@Param("userId") UUID userId, PageRequest pageRequest);
   ```

**Final code:**
```java
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
```

### 2. Test the Endpoint
1. Restart the application
2. Test the endpoint `GET /api/users/{userId}/tasks` in Postman

## Expected Result
The endpoint should return a 200 OK response with the tasks for the specified user ID, instead of a 500 Internal Server Error.
