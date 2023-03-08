package com.pgjbz.urlrequestconsumer.domain.service;

import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.repository.RequestRepository;

public final class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final JsonService jsonService;

    public RequestServiceImpl(final RequestRepository requestRepository,
            final JsonService jsonService) {
        this.requestRepository = requestRepository;
        this.jsonService = jsonService;
    }

    @Override
    public void save(Request request) {
        jsonService.serialize(request.headers())
                .ifPresent(json -> requestRepository.save(json, request.urlId()));
    }

}
