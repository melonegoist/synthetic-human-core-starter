package edu.t1.synthetichumancorestarter.core.modules.error_module;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException(String message) {
        super(message);
    }
}
