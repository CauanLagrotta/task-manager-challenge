package com.cauanlagrotta.task_manager_challenge.dto;

import jakarta.validation.constraints.Email;

import java.util.UUID;

public record UserResponseDTO(UUID id,
                              String name,
                              @Email String email) {
}
