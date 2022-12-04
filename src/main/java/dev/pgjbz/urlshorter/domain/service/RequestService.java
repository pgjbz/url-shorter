package dev.pgjbz.urlshorter.domain.service;

import dev.pgjbz.urlshorter.domain.model.Request;

public sealed interface RequestService permits RequestServiceImpl {
    void save(Request request);
}
