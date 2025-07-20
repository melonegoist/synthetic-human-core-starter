package edu.t1.synthetichumancorestarter.core.modules.command_module.queue;

public record QueueStatus(
        int currentSize,
        int remainingSize,
        long completedTasks
) {}
