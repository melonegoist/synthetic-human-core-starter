package edu.t1.synthetichumancorestarter.core.modules.audit_module.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.Arrays;

@With
@Builder
@Getter
@Setter
public class AuditEvent {
    private final String methodName;
    private final String description;
    private final String args;
    private final Object result;
    private final String timestamp;
    private final EventStatus status;

    public static AuditEventBuilder builder(Object[] args) {
        return new AuditEventBuilder()
                .args(Arrays.toString(args));
    }
}
