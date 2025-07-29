package edu.t1.synthetichumancorestarter.core.config;

import edu.t1.synthetichumancorestarter.core.modules.command_module.CommandProcessor;
import edu.t1.synthetichumancorestarter.core.modules.command_module.queue.CommandQueue;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CommonCommandHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.service.CriticalCommandsHandler;
import edu.t1.synthetichumancorestarter.core.modules.command_module.validation.CommandValidator;
import edu.t1.synthetichumancorestarter.core.modules.monitoring_module.OccupancyMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CommandProperties.class)
public class CommandAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommandProcessor commandProcessor(
            CommandQueue commandQueue,
            CriticalCommandsHandler criticalCommandsHandler,
            CommonCommandHandler commonCommandHandler,
            OccupancyMetrics occupancyMetrics
    ) {
        return new CommandProcessor(commandQueue, criticalCommandsHandler, occupancyMetrics, commonCommandHandler);
    }

}
