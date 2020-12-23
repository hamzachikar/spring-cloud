package com.microservices.limitsservice;

import com.microservices.limitsservice.beans.LimitsConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class LimitsConfigController {
    @Autowired
    private Config config;
    @Value("${message}")
    private String message;

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsConfiguration(){
        return new LimitsConfiguration(config.getMaximum(),config.getMinimum());
    }
    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
