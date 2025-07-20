package edu.t1.synthetichumancorestarter.core.modules.command_module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandLogger {
    private final Logger logger = LoggerFactory.getLogger(CommandLogger.class);

    public void log(String message) {
        logger.info("[Command]: {}", message);
    }
}
