package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.exceptions.TaskNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTasksByIdService {
  private final TaskRepository taskRepository;

  public DeleteTasksByIdService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void deleteTaskById(UUID id){
    var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    taskRepository.delete(task);
  }
}
