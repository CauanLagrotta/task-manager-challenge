package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetTasksByUserIdServiceTest {
  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private GetTasksByUserIdService getTasksByUserIdService;

  @Test
  void shouldReturnTasksForUser(){
    UUID userId = UUID.randomUUID();
    PageRequest pageRequest = PageRequest.of(0, 10);

    Task task = new Task();
    task.setId(UUID.randomUUID());
    task.setTitle("Task Title");
    task.setDescription("Task Description");
    task.setStatus(Status.PENDING);
    User user = new User();
    user.setId(userId);
    task.setUserId(user);

    Page<Task> taskPage = new PageImpl<>(List.of(task));

    when(taskRepository.findAllByUserId(userId, pageRequest)).thenReturn(taskPage);

    Page<TaskResponseDTO> result = getTasksByUserIdService.findAllByUserId(userId, pageRequest);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    assertEquals(1, result.getContent().size());
    assertEquals(task.getId(), result.getContent().get(0).id());
    assertEquals(task.getTitle(), result.getContent().get(0).title());
    verify(taskRepository).findAllByUserId(userId, pageRequest);
  }

  @Test
  void shouldReturnEmptyPageWhenNoTasksFound(){
    UUID userId = UUID.randomUUID();
    PageRequest pageRequest = PageRequest.of(0, 10);

    Page<Task> emptyPage = Page.empty();

    when(taskRepository.findAllByUserId(userId, pageRequest)).thenReturn(emptyPage);

    Page<TaskResponseDTO> result = getTasksByUserIdService.findAllByUserId(userId, pageRequest);

    assertNotNull(result);
    assertEquals(0, result.getTotalElements());
    assertTrue(result.getContent().isEmpty());
    verify(taskRepository).findAllByUserId(userId, pageRequest);
  }
}
