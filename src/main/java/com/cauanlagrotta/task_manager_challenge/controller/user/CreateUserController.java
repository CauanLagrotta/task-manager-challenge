package com.cauanlagrotta.task_manager_challenge.controller.user;

import com.cauanlagrotta.task_manager_challenge.dto.UserRequestDTO;
import com.cauanlagrotta.task_manager_challenge.dto.UserResponseDTO;
import com.cauanlagrotta.task_manager_challenge.service.user.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class CreateUserController {
  private final CreateUserService createUserService;

  public CreateUserController(CreateUserService createUserService) {
    this.createUserService = createUserService;
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto) {
    UserResponseDTO response = createUserService.execute(dto);
    return ResponseEntity.ok(response);
  }
}
