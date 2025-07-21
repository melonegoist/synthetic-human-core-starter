package edu.t1.synthetichumancorestarter.core.modules.audit_module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.Arrays;

@With
@Builder
@Getter
public class AuditEvent {

    @JsonProperty("method_name")
    private final String methodName;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("args")
    private final String args;

    @JsonProperty("result")
    private final Object result;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final String timestamp;

    @JsonProperty("status")
    private final EventStatus status;

    public static AuditEventBuilder builder(Object[] args) {
        return new AuditEventBuilder()
                .args(Arrays.toString(args));
    }
}
