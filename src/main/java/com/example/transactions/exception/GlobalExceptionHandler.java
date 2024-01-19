package com.example.transactions.exception;

import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.util.ControllerResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ControllerBaseResponse> handleCustomValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ControllerResponseUtil.getFailureResponse(ex.getMessage(), ex.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ControllerBaseResponse> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ControllerResponseUtil.getFailureResponse(ex.getMessage(), "500"));
    }
}
