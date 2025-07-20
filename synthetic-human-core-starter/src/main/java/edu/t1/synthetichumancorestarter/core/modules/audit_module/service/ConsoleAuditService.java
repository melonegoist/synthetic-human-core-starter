package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleAuditService implements AuditService {
    private static final Logger log = LoggerFactory.getLogger(ConsoleAuditService.class);

    @Override
    public void send(AuditEvent auditEvent) {
        log.info("[AUDIT] Auditing event: {}", auditEvent);
    }

}
