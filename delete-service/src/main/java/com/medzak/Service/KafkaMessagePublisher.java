package com.medzak.Service;

import com.medzak.config.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    public void sendMessageToTopic(String messages, int i) {
        CompletableFuture<SendResult<String, Object>> future = template.send(kafkaProducerConfig.createTopic().name(), messages);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message[" + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("sorry man cant send message=[" + "] due to: " + ex.getMessage());
            }

        });

    }
}
