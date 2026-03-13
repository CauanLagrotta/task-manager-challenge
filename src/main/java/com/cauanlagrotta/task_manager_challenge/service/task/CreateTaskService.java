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
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CreateTaskService {
  private final TaskRepository taskRepository;
  private final UserRepository userRepository;

  public CreateTaskService(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
  }

  public TaskResponseDTO execute(TaskRequestDTO dto){
    User user = userRepository.findById(dto.userId())
            .orElseThrow(UserNotFoundException::new);

    if(!Set.of(Status.PENDING, Status.IN_PROGRESS, Status.DONE).contains(dto.status())){
      throw new InvalidStatusException();
    }

    Task savedTask = taskRepository.save(dto.toTask(user));
    return new TaskResponseDTO(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus(), savedTask.getUserId().getId());
  }
}
