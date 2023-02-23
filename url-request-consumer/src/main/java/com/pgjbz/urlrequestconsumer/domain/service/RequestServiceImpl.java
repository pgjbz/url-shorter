package com.pgjbz.urlrequestconsumer.domain.service;

import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.repository.RequestRepository;

public final class RequestServiceImpl implements RequestService {
    
    private final RequestRepository requestRepository;

    public RequestServiceImpl(final RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

}
