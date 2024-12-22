package com.medzak.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {
    @KafkaListener(topics = "delete_topics", groupId = "delete_groupId")
    public void consume(String message) {
        log.info("received message: " + message);
    }
}
