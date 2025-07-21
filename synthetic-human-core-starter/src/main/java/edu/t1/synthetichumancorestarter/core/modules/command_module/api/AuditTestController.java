package edu.t1.synthetichumancorestarter.core.modules.command_module.api;

import edu.t1.synthetichumancorestarter.core.modules.audit_module.annotation.WeylandWatchingYou;
import edu.t1.synthetichumancorestarter.core.modules.audit_module.model.AuditMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditTestController {

    @WeylandWatchingYou(mode = AuditMode.CONSOLE, description = "test")
    @GetMapping("/log")
    public String log() {
        return "Weyland watching you!";
    }

}
