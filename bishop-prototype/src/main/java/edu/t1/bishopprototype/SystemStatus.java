package edu.t1.bishopprototype;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public record SystemStatus(
        AtomicInteger activeTasks,
        AtomicInteger queueCommands,
        Instant timestamp
) {}
