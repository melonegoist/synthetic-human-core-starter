package edu.t1.synthetichumancorestarter;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.aspect.AuditAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SyntheticHumanCoreStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SyntheticHumanCoreStarterApplication.class, args);

        AuditAspect aspect = context.getBean(AuditAspect.class);

//        SpringApplication.run(SyntheticHumanCoreStarterApplication.class, args);
        System.out.println(aspect.getClass().getName());
    }

}
