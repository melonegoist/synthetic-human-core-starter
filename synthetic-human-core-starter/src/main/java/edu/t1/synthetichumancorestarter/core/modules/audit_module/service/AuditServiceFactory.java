package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;

public interface AuditServiceFactory {
    AuditService getAuditService(AuditMode auditMode);
}
