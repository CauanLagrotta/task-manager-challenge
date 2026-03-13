package com.cauanlagrotta.task_manager_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(TaskManagerException.class)
  public ProblemDetail handleTaskManagerException(TaskManagerException e) {
    return e.toProblemDetail();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleMethodArgumenNotValidException(MethodArgumentNotValidException e) {
    var fieldErrors = e.getFieldErrors()
        .stream()
        .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
        .toList();

    var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pb.setTitle("Your request parameters didn't validate.");
    pb.setProperty("invalid-params", fieldErrors);

    return pb;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    var pb = ProblemDetail.forStatus(422);
    pb.setTitle("Invalid status.");
    pb.setDetail("All the available status are: 'PENDING', 'IN_PROGRESS' and 'DONE'");

    return pb;
  }

  private record InvalidParam(String name, String reason) { }
}
