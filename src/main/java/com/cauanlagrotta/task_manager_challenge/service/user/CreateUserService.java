package com.cauanlagrotta.task_manager_challenge.service.user;

import com.cauanlagrotta.task_manager_challenge.dto.UserRequestDTO;
import com.cauanlagrotta.task_manager_challenge.dto.UserResponseDTO;
import com.cauanlagrotta.task_manager_challenge.entity.User;
import com.cauanlagrotta.task_manager_challenge.exceptions.UserAlreadyExistsException;
import com.cauanlagrotta.task_manager_challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
  private final UserRepository userRepository;

  public CreateUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponseDTO execute(UserRequestDTO dto) {
    if (userRepository.existsByEmail(dto.email())) {
      throw new UserAlreadyExistsException();
    }
    User savedUser = userRepository.save(dto.toUser());
    return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
  }
}
