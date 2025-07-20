package edu.t1.synthetichumancorestarter.core.modules.command_module.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCommand(InvalidCommandException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Invalid command", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Validation failed: ", errors.toString()));
    }
}
