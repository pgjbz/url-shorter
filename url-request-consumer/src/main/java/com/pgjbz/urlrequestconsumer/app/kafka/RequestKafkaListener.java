package com.pgjbz.urlrequestconsumer.app.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.service.JsonService;
import com.pgjbz.urlrequestconsumer.domain.service.RequestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestKafkaListener {

    private final RequestService requestService;
    private final JsonService jsonService;

    @KafkaListener(topics = "request", groupId = "request-consumer")
    public void handle(@Payload String value) {
        log.info("receive payload: {}", value);
        jsonService.deserialize(value, Request.class)
                .ifPresent(requestService::save);
    }

}
