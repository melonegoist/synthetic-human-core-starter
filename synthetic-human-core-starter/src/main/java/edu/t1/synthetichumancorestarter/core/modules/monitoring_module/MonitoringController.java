package edu.t1.synthetichumancorestarter.core.modules.monitoring_module;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final OccupancyMetrics metrics;

    @GetMapping("/occupancy")
    public OccupancyStatus getOccupancyStatus() {
        return new  OccupancyStatus(
                metrics.getQueueSize(),
                metrics.getActiveTasks()
        );
    }

}
