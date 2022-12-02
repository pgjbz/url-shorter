package dev.pgjbz.urlshorter.infra.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.pgjbz.urlshorter.domain.service.JsonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonServiceImpl implements JsonService {
    
    private final ObjectMapper mapper;

    @Override
    public <T> Optional<String> toJsonString(T obj) {
        try {
            return Optional.of(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            log.error("error on transform json to string", e);
        }
        return Optional.empty();
    }

}
