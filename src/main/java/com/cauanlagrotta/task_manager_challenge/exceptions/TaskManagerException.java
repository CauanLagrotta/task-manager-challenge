package com.cauanlagrotta.task_manager_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TaskManagerException extends RuntimeException{
  public ProblemDetail toProblemDetail(){
    var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    pb.setTitle("Task Manager internal server exception");
    return pb;
  }
}
