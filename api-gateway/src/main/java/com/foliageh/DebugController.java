package com.foliageh;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class DebugController {
    private final EurekaClient eurekaClient;

    public DebugController(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping("/debug/eureka/instances")
    public Object listInstances() {
        return eurekaClient.getApplications()
                .getRegisteredApplications()
                .stream()
                .map(app -> app.getName() + " -> " + app.getInstances().stream()
                        .map(InstanceInfo::getHomePageUrl)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
