package com.cauanlagrotta.task_manager_challenge.dto;

import com.cauanlagrotta.task_manager_challenge.entity.Task;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskRequestDTO(@NotBlank String title,
                             String description,
                             @NotNull Status status,
                             @NotNull UUID userId) {

  public Task toTask(User user){
    return new Task(
        title,
        description,
        status,
        user
    );
  }
}
