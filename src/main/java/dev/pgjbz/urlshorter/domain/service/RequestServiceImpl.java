package dev.pgjbz.urlshorter.domain.service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.pgjbz.urlshorter.domain.repository.RequestRepository;

public final class RequestServiceImpl implements RequestService {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private final RequestRepository requestRepository;
    private final JsonService jsonService;

    public RequestServiceImpl(RequestRepository requestRepository, JsonService jsonService) {
        this.requestRepository = requestRepository;
        this.jsonService = jsonService;
    }

    @Override
    public void save(Map<String, String> headers) {
        executor.submit(() -> 
            jsonService.toJsonString(headers).ifPresent(requestRepository::save)
        );
    }

}
