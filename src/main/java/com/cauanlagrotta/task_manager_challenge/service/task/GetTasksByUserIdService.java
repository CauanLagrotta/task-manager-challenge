package com.cauanlagrotta.task_manager_challenge.service.task;

import com.cauanlagrotta.task_manager_challenge.dto.TaskResponseDTO;
import com.cauanlagrotta.task_manager_challenge.exceptions.UserNotFoundException;
import com.cauanlagrotta.task_manager_challenge.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTasksByUserIdService {
  private final TaskRepository taskRepository;

  public GetTasksByUserIdService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Page<TaskResponseDTO> findAllByUserId(UUID userId, PageRequest pageRequest){
    var tasks = taskRepository.findAllByUserId(userId, pageRequest);

//    if(!userId.equals(tasks.getContent().getFirst().getUserId())){
//      throw new UserNotFoundException();
//    }

    return tasks.map(TaskResponseDTO::fromEntity);
  }
}
