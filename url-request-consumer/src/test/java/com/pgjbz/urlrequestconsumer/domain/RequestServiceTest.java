package com.pgjbz.urlrequestconsumer.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.repository.RequestRepository;
import com.pgjbz.urlrequestconsumer.domain.service.JsonService;
import com.pgjbz.urlrequestconsumer.domain.service.RequestService;
import com.pgjbz.urlrequestconsumer.domain.service.RequestServiceImpl;

class RequestServiceTest {
    
    private RequestService requestService;
    private RequestRepository requestRepository;
    private JsonService jsonService;

    @BeforeEach
    void setup() {
        requestRepository = mock(RequestRepository.class);
        jsonService = mock(JsonService.class);
        requestService = new RequestServiceImpl(requestRepository, jsonService);
    }

    @Test
    void shouldSaveDataWhenJsonAreParsed() {
        final var request = new Request(Map.of("foo", "bar"), 1L);
        
        when(jsonService.serialize(any())).thenReturn(Optional.of("{\"foo\": \"bar\"}"));

        requestService.save(request);

        verify(requestRepository).save(anyString(), anyLong());
    }

    @Test
    void shouldNotSaveDataWhenJsonAreParsed() {
        final var request = new Request(Map.of("foo", "bar"), 1L);
        
        when(jsonService.serialize(any())).thenReturn(Optional.empty());

        requestService.save(request);

        verify(requestRepository, never()).save(anyString(), anyLong());
    }
}
