package edu.t1.synthetichumancorestarter.core.modules.command_module.exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
