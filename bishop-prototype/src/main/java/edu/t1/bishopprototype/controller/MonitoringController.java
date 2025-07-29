package edu.t1.bishopprototype.controller;

import edu.t1.bishopprototype.SystemStatus;
import edu.t1.synthetichumancorestarter.core.modules.monitoring_module.OccupancyMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final OccupancyMetrics metrics;

    @GetMapping("/ststus")
    public SystemStatus getStatus() {
        return new SystemStatus(
                metrics.getActiveTasks(),
                metrics.getQueueSize(),
                Instant.now()
        );
    }

    @GetMapping("/metrics")
    public Map<String, Object> getPrometheusMetrics() {
        return Map.of(
                "queue_size", metrics.getQueueSize(),
                "active_tasks", metrics.getActiveTasks()
        );
    }


}
