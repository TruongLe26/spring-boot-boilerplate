package com.truonglq.demo.controllers;

import com.truonglq.demo.dtos.responses.StandardApiResponse;
import com.truonglq.demo.services.kafka.KafkaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {

    KafkaService kafkaService;

    @GetMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<String> sendMessage() {
        return StandardApiResponse.success(kafkaService.sendMessage("hi"), "Message sent!");
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    StandardApiResponse<List<String>> getMessages() {
        return StandardApiResponse.success(kafkaService.getMessages(), "Done!");
    }

}
