package com.cauanlagrotta.task_manager_challenge.controller.task;

import com.cauanlagrotta.task_manager_challenge.dto.StatusUpdateDTO;
import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.enums.Status;
import com.cauanlagrotta.task_manager_challenge.service.task.UpdateStatusByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks/{id}/status")
public class UpdateStatusByIdController {
  private final UpdateStatusByIdService updateStatusByIdService;

  public UpdateStatusByIdController(UpdateStatusByIdService updateStatusByIdService) {
    this.updateStatusByIdService = updateStatusByIdService;
  }

  @PatchMapping
  public TaskResponseDTO updateStatusById(@PathVariable UUID id, @RequestBody StatusUpdateDTO dto) {
    return updateStatusByIdService.updateStatusById(id, dto.status());
  }
}
