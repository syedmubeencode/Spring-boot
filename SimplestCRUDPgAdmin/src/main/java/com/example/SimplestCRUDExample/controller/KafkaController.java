package com.example.SimplestCRUDExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "my_topic";

    // 1. PRODUCER: Send a message via Browser/Postman
    // URL: http://localhost:8080/api/kafka/publish?msg=HelloKafka
    @GetMapping("/publish")
    public String sendMessage(@RequestParam("msg") String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Successfully sent: " + message;
    }

    // 2. CONSUMER: This runs automatically when a message hits the topic
    @KafkaListener(topics = TOPIC, groupId = "my-test-group")
    public void consume(String message) {
        System.out.println("------------------------------------------");
        System.out.println("KAFKA CONSUMER RECEIVED: " + message);
        System.out.println("------------------------------------------");
    }
}