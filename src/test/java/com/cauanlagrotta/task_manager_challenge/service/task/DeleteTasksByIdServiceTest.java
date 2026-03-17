package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.exceptions.TaskNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteTasksByIdServiceTest {
  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private DeleteTasksByIdService deleteTasksByIdService;

  @Test
  void shouldDeleteTaskSuccessfully(){
    UUID taskId = UUID.randomUUID();

    Task task = new Task();
    task.setId(taskId);
    task.setTitle("Task Title");
    task.setDescription("Task Description");
    task.setStatus(Status.PENDING);
    task.setUserId(new User());

    when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

    deleteTasksByIdService.deleteTaskById(taskId);

    verify(taskRepository).delete(task);
    assertDoesNotThrow(() -> TaskNotFoundException.class);
  }
}