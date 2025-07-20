package edu.t1.synthetichumancorestarter.core.modules.command_module.exceptions;

public record ErrorResponse(
        String error,
        String message
) {}
