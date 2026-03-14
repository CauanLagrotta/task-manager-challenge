package com.cauanlagrotta.task_manager_challenge.dto;

import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskResponseDTO(UUID id,
                              @NotBlank String title,
                              String description,
                              @NotNull Status status,
                              UUID userId) {

  public static TaskResponseDTO fromEntity(Task task){
    return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getUserId().getId());
  }
}
