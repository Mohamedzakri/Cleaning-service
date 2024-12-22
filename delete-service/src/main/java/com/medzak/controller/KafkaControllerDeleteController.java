package com.medzak.controller;

import com.medzak.Service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka-listener")
public class KafkaControllerDeleteController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/pub")
    private String Hi(){
        return "hi world";
    }

    @GetMapping("/publish/{messages}")
    public ResponseEntity<?> publishMessage(@PathVariable String messages){
        try{
            for (int i = 0; i < 100000; i++){
                kafkaMessagePublisher.sendMessageToTopic(messages, i);
            }
            return ResponseEntity.ok("message published successfully ..");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
