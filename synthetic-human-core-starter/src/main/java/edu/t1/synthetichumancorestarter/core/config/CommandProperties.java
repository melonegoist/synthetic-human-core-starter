package edu.t1.synthetichumancorestarter.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weyland.command")
@Getter
@Setter
public class CommandProperties {

    private int maxQueueSize = 100;
    private int queueWarningThreshold = 100;
    private long executionTimeout = 30;

}
