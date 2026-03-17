package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskRequestDTO;
import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.exceptions.InvalidStatusException;
import com.cauanlagrotta.task_manager_challenge.exceptions.UserNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import com.cauanlagrotta.task_manager_challenge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTaskServiceTest {
  @Mock
  private TaskRepository taskRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private CreateTaskService createTaskService;

  @Test
  void shouldCreatedTaskSuccessfully(){
    UUID userId = UUID.randomUUID();
    User user = new User();
    TaskRequestDTO reqDTO = new TaskRequestDTO("Task Title", "Task Description", Status.PENDING, userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
      Task savedTask = invocation.getArgument(0);
      savedTask.setId(UUID.randomUUID());
      return savedTask;
    });

    TaskResponseDTO result = createTaskService.execute(reqDTO);

    boolean correctStatus = Set.of(Status.DONE, Status.IN_PROGRESS, Status.PENDING).contains(reqDTO.status());

    assertNotNull(result);
    assertNotNull(result.id());
    assertDoesNotThrow(() -> InvalidStatusException.class);
    assertDoesNotThrow(() -> UserNotFoundException.class);
    verify(userRepository).findById(userId);
    verify(taskRepository).save(any(Task.class));
    assertTrue(correctStatus);
  }

  @Test
  void shouldNotCreateATaskIfIsMissingAnUser(){
    UUID userId = UUID.randomUUID();
    TaskRequestDTO reqDTO = new TaskRequestDTO("Task Title", "Task Description", Status.PENDING, userId);

    User user = new User();
    user.setId(userId);

    Task task = new Task();
    task.setTitle(reqDTO.title());
    task.setDescription(reqDTO.description());
    task.setStatus(reqDTO.status());
    task.setUserId(user);

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> createTaskService.execute(reqDTO));

    assertDoesNotThrow(() -> taskRepository.save(task));
    assertDoesNotThrow(() -> InvalidStatusException.class);
  }
}