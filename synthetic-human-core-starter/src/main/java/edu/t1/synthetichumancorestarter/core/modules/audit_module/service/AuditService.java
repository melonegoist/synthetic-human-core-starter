package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;

public interface AuditService {
    void send(AuditEvent auditEvent);
}
