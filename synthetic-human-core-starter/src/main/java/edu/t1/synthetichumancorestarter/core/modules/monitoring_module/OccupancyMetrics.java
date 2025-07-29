package edu.t1.synthetichumancorestarter.core.modules.monitoring_module;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Getter
public class OccupancyMetrics {

    private final MeterRegistry meterRegistry;
    private final AtomicInteger queueSize = new AtomicInteger(0);
    private final AtomicInteger activeTasks = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        Gauge.builder("android.occupancy.queue_size", queueSize::get)
                .description("The queue size of the queue")
                .register(meterRegistry);

        Gauge.builder("android.occupancy.active_tasks", activeTasks::get)
                .description("The number of active tasks in the queue")
                .register(meterRegistry);
    }

    public void incrementQueueSize() {
        queueSize.incrementAndGet();
    }

    public void decrementQueueSize() {
        queueSize.decrementAndGet();
    }

    public void incrementActiveTasks() {
        queueSize.incrementAndGet();
    }

    public void decrementActiveTasks() {
        queueSize.decrementAndGet();
    }

}
