package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaAuditService implements AuditService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(AuditEvent auditEvent) {
        kafkaTemplate.send("audit-logs", serialize(auditEvent));
    }

    private String serialize(AuditEvent auditEvent) {
        try {
            return objectMapper.writeValueAsString(auditEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize AuditEvent", e);
        }
    }
}
