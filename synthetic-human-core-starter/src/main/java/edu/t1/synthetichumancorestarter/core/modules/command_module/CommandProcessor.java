package edu.t1.synthetichumancorestarter.core.modules.command_module;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;
import edu.t1.synthetichumancorestarter.core.modules.command_module.queue.CommandQueue;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CommonCommandHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CriticalCommandsHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.validation.CommandValidator;
import edu.t1.synthetichumancorestarter.core.modules.error_module.InvalidCommandException;
import edu.t1.synthetichumancorestarter.core.modules.monitoring_module.OccupancyMetrics;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandProcessor {

    private final CommandQueue commandQueue;
    private final CriticalCommandsHandler criticalCommandsHandler;
    private final OccupancyMetrics occupancyMetrics;
    private final CommonCommandHandler commonCommandHandler;

    @WeylandWatchingYou(mode= AuditMode.KAFKA, description = "Command dispatch processing")
    public void dispatch(Command command) {
        switch (command.priority()) {
            case COMMON -> handleCommon(command);
            case CRITICAL -> handleCritical(command);
            default -> throw new InvalidCommandException("unknown command priority" + command.priority());
        }
    }

    private void handleCommon(Command command) {
        occupancyMetrics.incrementActiveTasks();
        commandQueue.addTask(() -> {
            try {
                commonCommandHandler.execute(command);
            } finally {
                occupancyMetrics.decrementActiveTasks();
            }
        });
    }

    private void handleCritical(Command command) {
        criticalCommandsHandler.execute(command);
    }

}
