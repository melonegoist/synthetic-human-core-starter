package edu.t1.synthetichumancorestarter.core.modules.monitoring_module;

import java.util.concurrent.atomic.AtomicInteger;

public record OccupancyStatus(
        AtomicInteger queueSize,
        AtomicInteger activeTasks
) {}
