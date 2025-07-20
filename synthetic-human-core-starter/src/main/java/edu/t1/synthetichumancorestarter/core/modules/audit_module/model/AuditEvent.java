package edu.t1.synthetichumancorestarter.core.modules.audit_module.model;

public record AuditEvent (
   String methodName,
   String description,
   Object[] args,
   Object result,
   String timestamp,
   EventStatus status
) {}
