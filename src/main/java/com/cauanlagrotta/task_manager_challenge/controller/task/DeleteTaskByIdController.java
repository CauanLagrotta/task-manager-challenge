package com.cauanlagrotta.task_manager_challenge.controller.task;

import com.cauanlagrotta.task_manager_challenge.service.task.DeleteTasksByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks/{id}")
public class DeleteTaskByIdController {
  private final DeleteTasksByIdService deleteTasksByIdService;

  public DeleteTaskByIdController(DeleteTasksByIdService deleteTasksByIdService) {
    this.deleteTasksByIdService = deleteTasksByIdService;
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteTaskById(@PathVariable UUID id){
    deleteTasksByIdService.deleteTaskById(id);
    return ResponseEntity.noContent().build();
  }
}
