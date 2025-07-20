package edu.t1.synthetichumancorestarter.core.modules.audit_module.model;

import lombok.With;

@With
public record AuditEvent (
   String methodName,
   String description,
   Object[] args,
   Object result,
   String timestamp,
   EventStatus status
) {
//    public AuditEvent withResult(Object newResult) {
//        return new AuditEvent(
//                this.methodName,
//                this.description,
//                this.args,
//                newResult,
//                this.timestamp,
//                this.status
//        );
//    }
}
