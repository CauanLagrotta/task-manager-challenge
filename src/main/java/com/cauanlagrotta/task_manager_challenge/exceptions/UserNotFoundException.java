package com.cauanlagrotta.task_manager_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends TaskManagerException {
  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pb.setTitle("User not found.");

    return pb;
  }
}
