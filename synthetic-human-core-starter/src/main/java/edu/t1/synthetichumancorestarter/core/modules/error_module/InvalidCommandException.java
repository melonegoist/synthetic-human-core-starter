package edu.t1.synthetichumancorestarter.core.modules.error_module;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
