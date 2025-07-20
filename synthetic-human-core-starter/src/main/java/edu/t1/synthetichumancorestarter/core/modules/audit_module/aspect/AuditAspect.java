package edu.t1.synthetichumancorestarter.core.modules.audit_module.aspect;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditEvent;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.EventStatus;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.service.AuditServiceFactory;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditServiceFactory serviceFactory;

    @Around("@annotation(audit)")
    public Object logAudit(ProceedingJoinPoint pjp, WeylandWatchingYou audit) throws Throwable {
        AuditEvent event = createEvent(pjp, audit);

        try {
            Object result = pjp.proceed();
            event = event.withResult(result).withStatus(EventStatus.SUCCESS);
            return result;
        } catch (Exception e) {
            event = event.withResult(e.getMessage()).withStatus(EventStatus.FAILURE);
            throw e;
        } finally {
            serviceFactory.getAuditService(audit.mode()).send(event);
        }
    }

    private AuditEvent createEvent(ProceedingJoinPoint pjp, WeylandWatchingYou audit) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        return new AuditEvent(
                method.getName(),
                audit.description(),
                pjp.getArgs(),
                null, // TODO
                Instant.now().toString(),
                EventStatus.IN_PROGRESS
        );
    }
}
