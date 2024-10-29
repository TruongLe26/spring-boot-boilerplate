package com.truonglq.demo.kafka.consumer;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Getter
@Component
public class TopicConsumer {

    private final List<String> messages = new ArrayList<>();

//    @KafkaListener(topics = "demo", groupId = "my-group")
//    public void listen(String message) {
//        synchronized (messages) {
//            System.out.println(message);
//            messages.add(message);
//        }
//    }

    @KafkaListener(topics = "demo2", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        synchronized (messages) {
            System.out.println("Received: " + record.value() + " with offset: " + record.offset() + " and topic: " + record.topic());
            messages.add(record.value());
        }
    }

    public List<String> getMessages() {
        return messages;
    }

}
