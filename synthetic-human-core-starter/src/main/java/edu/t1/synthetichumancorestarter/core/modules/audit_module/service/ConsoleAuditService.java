package edu.t1.synthetichumancorestarter.core.modules.audit_module.service;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsoleAuditService implements AuditService {
    private static final Logger log = LoggerFactory.getLogger(ConsoleAuditService.class);

    @Override
    public void send(AuditEvent auditEvent) {
        log.info("\n[AUDIT] Event{\n" +
                        "  methodName={},\n" +
                        "  description={},\n" +
                        "  args={},\n" +
                        "  result={},\n" +
                        "  timestamp={},\n" +
                        "  status={}\n" +
                        "}",
                auditEvent.getMethodName(),
                auditEvent.getDescription(),
                auditEvent.getArgs(),
                auditEvent.getResult(),
                auditEvent.getTimestamp(),
                auditEvent.getStatus()
        );
    }

}
