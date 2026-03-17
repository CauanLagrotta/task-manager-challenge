package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.exceptions.StatusCannotChangeFromDone;
import com.cauanlagrotta.task_manager_challenge.exceptions.TaskNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateStatusByIdServiceTest {
  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private UpdateStatusByIdService updateStatusByIdService;

  @Test
  void shouldUpdateTaskStatusSuccessfully(){
    UUID taskId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    
    Task task = new Task();
    task.setId(taskId);
    task.setTitle("Task Title");
    task.setDescription("Task Description");
    task.setStatus(Status.PENDING);
    User user = new User();
    user.setId(userId);
    task.setUserId(user);

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    TaskResponseDTO result = updateStatusByIdService.updateStatusById(taskId, Status.IN_PROGRESS);

    assertNotNull(result);
    assertEquals(taskId, result.id());
    assertEquals(Status.IN_PROGRESS, result.status());
    verify(taskRepository).findById(taskId);
    verify(taskRepository).save(task);
  }

  @Test
  void shouldThrowTaskNotFoundExceptionWhenTaskNotFound(){
    UUID taskId = UUID.randomUUID();

    when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> updateStatusByIdService.updateStatusById(taskId, Status.IN_PROGRESS));
    verify(taskRepository).findById(taskId);
    verify(taskRepository, never()).save(any(Task.class));
  }

  @Test
  void shouldThrowStatusCannotChangeFromDoneWhenTaskIsDone(){
    UUID taskId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    
    Task task = new Task();
    task.setId(taskId);
    task.setTitle("Task Title");
    task.setDescription("Task Description");
    task.setStatus(Status.DONE);
    User user = new User();
    user.setId(userId);
    task.setUserId(user);

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

    assertThrows(StatusCannotChangeFromDone.class, () -> updateStatusByIdService.updateStatusById(taskId, Status.PENDING));
    verify(taskRepository).findById(taskId);
    verify(taskRepository, never()).save(any(Task.class));
  }

  @Test
  void shouldUpdateStatusFromPendingToDone(){
    UUID taskId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    
    Task task = new Task();
    task.setId(taskId);
    task.setTitle("Task Title");
    task.setDescription("Task Description");
    task.setStatus(Status.PENDING);
    User user = new User();
    user.setId(userId);
    task.setUserId(user);

    when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    TaskResponseDTO result = updateStatusByIdService.updateStatusById(taskId, Status.DONE);

    assertNotNull(result);
    assertEquals(Status.DONE, result.status());
    verify(taskRepository).findById(taskId);
    verify(taskRepository).save(task);
  }
}
