package dev.pgjbz.urlshorter.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.pgjbz.urlshorter.domain.model.Request;
import dev.pgjbz.urlshorter.domain.repository.RequestRepository;

public class RequestServiceTest {
    private RequestService requestService;
    private RequestRepository repository;
    private JsonService jsonService;

    @BeforeEach
    void setup() {
        repository = mock(RequestRepository.class);
        jsonService = mock(JsonService.class);
        requestService = new RequestServiceImpl(repository, jsonService);
    }

    @Test
    void testSaveRequestExpectedSucces() {
        final var urlId = 1L;
        final var headers = Map.of("foo", "bar");
        final var headersString = "{\"foo\":\"bar\"}";
        final var request = new Request(headers, urlId);


        when(jsonService.toJsonString(any())).thenReturn(Optional.of(headersString));

        assertDoesNotThrow(() -> requestService.save(request));

        verify(repository, timeout(100)).save(1L, headersString);
    }

    @Test
    void testSaveRequestNeverSave() {
        final var urlId = 1L;
        final var headers = Map.of("foo", "bar");
        final var headersString = "{\"foo\":\"bar\"}";
        final var request = new Request(headers, urlId);


        when(jsonService.toJsonString(any())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> requestService.save(request));

        verify(repository, never()).save(1L, headersString);
    }

}
