package edu.t1.synthetichumancorestarter.core.modules.command_module.service;

import edu.t1.synthetichumancorestarter.core.modules.command_module.CommandLogger;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonCommandHandler implements CommandExecutor {

    private final CommandLogger logger;

    @Override
    public void execute(Command command) {
        logger.log(getCommonCommandResult(command));
    }

    private String getCommonCommandResult(Command command) {
        if (command.priority() == Priority.CRITICAL) return "ERROR! CRITICAL SITUATION!";

        return String.format("New command received!\n%s", command.toString());

        //TODO: check
    }
}
