package edu.t1.synthetichumancorestarter.core.modules.command_module.queue;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.error_module.QueueOverflowException;
import edu.t1.synthetichumancorestarter.core.modules.monitoring_module.OccupancyMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@RequiredArgsConstructor
public class CommandQueue {

    private final OccupancyMetrics metrics;
    @Qualifier("commandExecutor")
    private final ThreadPoolTaskExecutor executor;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);

    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "Auditing adding task in queue...")
    public void addTask(Runnable task) throws QueueOverflowException {
        if (executor.getQueueCapacity() == 0) {
            throw new QueueOverflowException("command queue is full!");
        }

        metrics.incrementQueueSize();
        queue.add(() -> {
            metrics.decrementQueueSize();
            metrics.incrementActiveTasks();

            try {
                task.run();
            } finally {
                metrics.decrementActiveTasks();
            }
        });

        executor.execute(queue.peek());
    }

    public int getQueueSize() {
        return queue.size();
    }

    public int getRemainingCapacity() {
        return queue.remainingCapacity();
    }

}
