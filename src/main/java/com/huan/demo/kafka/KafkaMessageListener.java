package com.huan.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "my-topic")
    public void listen(String message) {
        System.out.println("Received: " + message);
    }
}