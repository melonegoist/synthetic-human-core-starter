package edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WeylandWatchingYou {
    AuditMode mode() default AuditMode.CONSOLE;
    String description() default "";
}
