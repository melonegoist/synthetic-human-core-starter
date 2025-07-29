package edu.t1.bishopprototype.controller;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import edu.t1.synthetichumancorestarter.core.modules.command_module.CommandProcessor;
import edu.t1.synthetichumancorestarter.core.modules.command_module.api.CommandRequest;
import edu.t1.synthetichumancorestarter.core.modules.command_module.api.CommandResponse;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Command;
import edu.t1.synthetichumancorestarter.core.modules.command_module.model.Priority;
import edu.t1.synthetichumancorestarter.core.modules.error_module.InvalidCommandException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommandController {

    private final CommandProcessor processor;

    @PostMapping
    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "Submit new command")
    public ResponseEntity<CommandResponse> submitCommand(@RequestBody CommandRequest request) {
        Command command = transferToCommand(request);
        processor.dispatch(command);

        return ResponseEntity.accepted()
                .body(new CommandResponse(
                        "Command received",
                        command.description(),
                        Instant.now().toString()
                ));
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
