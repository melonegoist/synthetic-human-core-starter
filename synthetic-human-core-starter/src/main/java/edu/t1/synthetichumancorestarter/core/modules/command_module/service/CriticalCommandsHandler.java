package edu.t1.synthetichumancorestarter.core.modules.command_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.command_module.CommandLogger;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CriticalCommandsHandler implements CommandExecutor {

    private final CommandLogger logger;

    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "Auditing critical command execution...")
    @Override
    public void execute(Command command) {
        logger.log(getCriticalCommandResult(command));
        // TODO
    }

    @WeylandWatchingYou(mode= AuditMode.CONSOLE, description = "Testing Critical Command")
    private String getCriticalCommandResult(Command command) {
        if (command.priority() == Priority.COMMON) return "Error! Wrong command priority!";

        Random random = new Random();
        int criticalRate = random.nextInt(100);

        return String.format("Warning! Critical command received!\nCritical rate is %d\nAuthor: %s\nTime: %s\nDescription:\n\"%s\"", criticalRate, command.author(), command.time(), command.description());
    }
}
