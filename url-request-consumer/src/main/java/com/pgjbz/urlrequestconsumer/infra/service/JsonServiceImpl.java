package com.pgjbz.urlrequestconsumer.infra.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgjbz.urlrequestconsumer.domain.service.JsonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonServiceImpl implements JsonService {

    private final ObjectMapper objectMapper;
    
    @Override
    public <T> Optional<String> serialize(T input) {
        try {
            log.info("try to serlize object to json");
            return Optional.ofNullable(objectMapper.writeValueAsString(input));
        } catch (Exception e) {
            log.error("fail to serialize object to json: {}", e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> deserialize(String json, Class<T> clazz) {
        try {
            log.info("try to parse json: {}", json);
            return Optional.ofNullable(objectMapper.readValue(json, clazz));
        } catch (Exception e) {
            log.error("fail to read value: {}", e.getMessage(), e);
        }
        return Optional.empty();
    }

}
