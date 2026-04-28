package com.example.SimplestCRUDExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
// This class will only be created if kafka.enabled is true in application.properties
@ConditionalOnProperty(value = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaController {

    @Autowired(required = false) // required=false prevents app crash if bean isn't created
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "my_topic";

    @GetMapping("/publish")
    public String sendMessage(@RequestParam("msg") String message) {
        if (kafkaTemplate == null) {
            return "Kafka is currently disabled.";
        }
        kafkaTemplate.send(TOPIC, message);
        return "Successfully sent: " + message;
    }

    @KafkaListener(topics = TOPIC, groupId = "my-test-group")
    public void consume(String message) {
        System.out.println("------------------------------------------");
        System.out.println("KAFKA CONSUMER RECEIVED: " + message);
        System.out.println("------------------------------------------");
    }
}