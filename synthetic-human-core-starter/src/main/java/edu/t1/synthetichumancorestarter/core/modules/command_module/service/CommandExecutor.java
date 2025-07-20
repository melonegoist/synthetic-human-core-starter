package edu.t1.synthetichumancorestarter.core.modules.command_module.service;

import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;

public interface CommandExecutor {
    void execute(Command command);
}
