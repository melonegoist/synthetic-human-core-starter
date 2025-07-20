package edu.t1.synthetichumancorestarter.core.modules.command_module.exceptions;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException(String message) {
        super(message);
    }
}
