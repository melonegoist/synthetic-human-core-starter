package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaAuditService implements AuditService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(AuditEvent auditEvent) {
        kafkaTemplate.send("audit-logs", serialize(auditEvent));
    }
}
