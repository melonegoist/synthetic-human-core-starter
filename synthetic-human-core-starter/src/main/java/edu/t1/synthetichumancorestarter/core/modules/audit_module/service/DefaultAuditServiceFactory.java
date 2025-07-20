package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultAuditServiceFactory implements AuditServiceFactory {

    private final ConsoleAuditService consoleAuditService;
    private final KafkaAuditService kafkaAuditService;

    @Override
    public AuditService getAuditService(AuditMode auditMode) {
        return switch (auditMode) {
            case CONSOLE -> consoleAuditService;
            case KAFKA -> kafkaAuditService;
            default -> throw new IllegalArgumentException("Unknown audit mode" + auditMode);
        };
    }

}
