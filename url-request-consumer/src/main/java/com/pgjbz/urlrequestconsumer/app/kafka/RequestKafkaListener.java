package com.pgjbz.urlrequestconsumer.app.kafka;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.service.RequestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestKafkaListener {

    private final RequestService requestService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "request", groupId = "request-consumer")
    public void handle(@Payload String value) {
        deserializeRequest(value)
                .ifPresent(requestService::save);
    }

    private Optional<Request> deserializeRequest(final String input) {
        try {
            log.info("try to read value: {}", input);
            return Optional.ofNullable(objectMapper.readValue(input, Request.class));
        } catch (Exception e) {
            log.error("error on read value: {}", input);
            return Optional.empty();
        }
    }

}
