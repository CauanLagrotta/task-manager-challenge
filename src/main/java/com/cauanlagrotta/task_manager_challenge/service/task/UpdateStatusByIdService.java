package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.exceptions.StatusCannotChangeFromDone;
import com.cauanlagrotta.task_manager_challenge.exceptions.TaskNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateStatusByIdService {
  private final TaskRepository taskRepository;

  public UpdateStatusByIdService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskResponseDTO updateStatusById(UUID id, Status status) {
    var task = taskRepository.findById(id)
        .orElseThrow(TaskNotFoundException::new);

    if(task.getStatus() == Status.DONE){
      throw new StatusCannotChangeFromDone();
    }

    task.setStatus(status);
    return TaskResponseDTO.fromEntity(taskRepository.save(task));
  }
}
