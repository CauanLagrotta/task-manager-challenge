package com.cauanlagrotta.task_manager_challenge.dto;

import com.cauanlagrotta.task_manager_challenge.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String name,
                             @NotBlank @Email String email) {

  public User toUser(){
    return new User(
        name,
        email
    );
  }
}
