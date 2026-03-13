package com.cauanlagrotta.task_manager_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidStatusException extends TaskManagerException {
  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pb.setTitle("Invalid status.");
    pb.setDetail("All the available status are: 'PENDING', 'IN_PROGRESS' and 'DONE'");

    return pb;
  }
}
