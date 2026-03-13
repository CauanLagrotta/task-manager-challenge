package com.cauanlagrotta.task_manager_challenge.controller.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskRequestDTO;
import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.service.task.CreateTaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/task")
public class CreateTaskController {
  private final CreateTaskService createTaskService;

  public CreateTaskController(CreateTaskService createTaskService) {
    this.createTaskService = createTaskService;
  }

  @PostMapping
  public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO dto){
    TaskResponseDTO response = createTaskService.execute(dto);
    return ResponseEntity.ok(response);
  }
}
