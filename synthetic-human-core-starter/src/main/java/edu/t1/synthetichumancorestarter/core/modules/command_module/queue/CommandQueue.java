package edu.t1.synthetichumancorestarter.core.modules.command_module.queue;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.command_module.exceptions.QueueOverflowException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class CommandQueue {

    private final ThreadPoolExecutor executor;
    private final MeterRegistry meterRegistry;

    public CommandQueue(@Value("${command.queue.core-size:5}") int corePoolSize, @Value("${command.queue.max-size:100}") int maxQueueSize, MeterRegistry meterRegistry) {
        this.executor = new ThreadPoolExecutor(
                corePoolSize,
                corePoolSize * 2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(maxQueueSize),
                new NamedThreadFactory("command-pool")
        );

        this.meterRegistry = meterRegistry;
    }

    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "Auditing adding task in queue...")
    public void addTask(Runnable task) throws QueueOverflowException {
        if (executor.getQueue().remainingCapacity() == 0) {
            throw new QueueOverflowException("command queue is full!");
        }

        executor.execute(task);
        updateMetrics();
    }

    private void updateMetrics() {
        meterRegistry.gauge("command.queue.size", executor.getQueue().size());
    }
}
