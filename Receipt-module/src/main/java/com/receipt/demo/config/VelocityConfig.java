package com.receipt.demo.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class VelocityConfig {

    @Bean
    public VelocityEngine velocityEngine() {
        Properties props = new Properties();
        // Load templates from src/main/resources/templates
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",
                  "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        props.put("input.encoding", "UTF-8");
        props.put("output.encoding", "UTF-8");

        VelocityEngine engine = new VelocityEngine();
        engine.init(props);
        return engine;
    }
}

