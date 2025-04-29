package project.ecommerce.api.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.model.WebResponse;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<WebResponse<Object>> constrainViolationException(ConstraintViolationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(WebResponse.builder().error(true).message(exception.getMessage()).build());
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<WebResponse<Object>> apiException(ResponseStatusException exception) {
    return ResponseEntity.status(exception.getStatusCode())
        .body(WebResponse.builder()
            .error(true)
            .message(exception.getReason())
            .status(exception.getStatusCode().value())
            .build());
  }
}
