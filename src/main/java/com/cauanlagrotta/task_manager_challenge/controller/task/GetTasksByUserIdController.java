package com.cauanlagrotta.task_manager_challenge.controller.task;

import com.cauanlagrotta.task_manager_challenge.dto.ApiResponse;
import com.cauanlagrotta.task_manager_challenge.dto.PaginationResponse;
import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.service.task.GetTasksByUserIdService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/tasks")
public class GetTasksByUserIdController {
  private final GetTasksByUserIdService getTasksByUserIdService;

  public GetTasksByUserIdController(GetTasksByUserIdService getTasksByUserIdService) {
    this.getTasksByUserIdService = getTasksByUserIdService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<TaskResponseDTO>> listTasks(@PathVariable("userId")UUID userId,
                                                                @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

    var pageResponse = getTasksByUserIdService.findAllByUserId(userId, PageRequest.of(page, pageSize));

    return ResponseEntity.ok(new ApiResponse<>(
        Map.of("totalTasks", pageResponse.getTotalElements()),
        pageResponse.getContent(),
        PaginationResponse.fromPage(pageResponse)
    ));
  }
}
