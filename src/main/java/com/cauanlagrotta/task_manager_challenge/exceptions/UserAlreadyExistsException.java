package com.cauanlagrotta.task_manager_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistsException extends TaskManagerException {
  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    pb.setTitle("User already exists.");

    return pb;
  }
}
