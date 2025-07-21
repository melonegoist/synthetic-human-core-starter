package edu.t1.synthetichumancorestarter.core.modules.error_module;

import edu.t1.synthetichumancorestarter.core.modules.command_module.queue.CommandQueue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final CommandQueue commandQueue;

    public GlobalExceptionHandler(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

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

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleQueueFull(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse("QUEUE OVERFLOW", e.getMessage() + Map.of(
                        "max queue size", 100,
                        "current_size", commandQueue.getQueueSize()
                )
                ));
    }
}
