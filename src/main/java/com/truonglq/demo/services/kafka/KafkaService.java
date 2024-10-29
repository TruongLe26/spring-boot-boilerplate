package com.truonglq.demo.services.kafka;

import com.truonglq.demo.kafka.consumer.TopicConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TopicConsumer topicConsumer;

    private final String topicName = "demo";
    private final String specialTopicName = "demo2";

    public String sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
        return message;
    }

    public String sendMessageToPartition(String message, Integer partition) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(specialTopicName, partition, null, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] to partition " + result.getRecordMetadata().partition() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
        return message;
    }

    public List<String> getMessages() {
        return topicConsumer.getMessages();
    }

}
