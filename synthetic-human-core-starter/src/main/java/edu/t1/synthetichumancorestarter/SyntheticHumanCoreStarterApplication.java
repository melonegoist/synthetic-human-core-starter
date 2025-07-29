package edu.t1.synthetichumancorestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SyntheticHumanCoreStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyntheticHumanCoreStarterApplication.class, args);
    }

}
