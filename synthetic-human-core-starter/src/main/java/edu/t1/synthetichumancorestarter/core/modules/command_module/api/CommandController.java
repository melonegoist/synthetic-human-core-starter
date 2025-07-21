package edu.t1.synthetichumancorestarter.core.modules.command_module.api;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.error_module.InvalidCommandException;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Priority;
import edu.t1.synthetichumancorestarter.core.modules.command_module.queue.CommandQueue;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CommonCommandHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CriticalCommandsHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.validation.CommandValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;

@RestController
@RequestMapping("/api/commands")
@RequiredArgsConstructor
public class CommandController {

    private final CommandQueue commandQueue;
    private final CommandValidator commandValidator; // TODO
    private final CriticalCommandsHandler criticalCommandsHandler;
    private final CommonCommandHandler commonCommandHandler;

    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "Auditing adding command...")
    @PostMapping
    public ResponseEntity<CommandResponse> addCommand(@Valid @RequestBody CommandRequest request) throws InvalidCommandException { // TODO
        Command command = transferToCommand(request);

        switch (command.priority()) {
            case CRITICAL:
                criticalCommandsHandler.execute(command);
                break;
            case COMMON:
                commandQueue.addTask(() -> commonCommandHandler.execute(command));
                break;
        }

        return ResponseEntity.accepted().body(
                new CommandResponse("Command received", command.time())
        );
    }

    private Command transferToCommand(CommandRequest request) throws InvalidCommandException {
        Priority priority;

        try {
            priority = Priority.valueOf(request.priority());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid priority value: \"" + request.priority() + "\". Allowed values are: " + Arrays.toString(Priority.values()));
        }

        return new Command(
                request.description(),
                priority,
                request.author(),
                Instant.now().toString()
        );
    }
}




