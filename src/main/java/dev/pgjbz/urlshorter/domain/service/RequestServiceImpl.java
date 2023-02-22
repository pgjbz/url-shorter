package dev.pgjbz.urlshorter.domain.service;

import dev.pgjbz.urlshorter.domain.model.Request;

public final class RequestServiceImpl implements RequestService {

    private final MessageService messageService;
    private final JsonService jsonService;

    public RequestServiceImpl(final MessageService messageService, final JsonService jsonService) {
        this.jsonService = jsonService;
        this.messageService = messageService;
    }

    @Override
    public void save(Request request) {
        jsonService.toJsonString(request)
            .ifPresent(json -> messageService.sendMessage(TARGET, json));
    }

}
