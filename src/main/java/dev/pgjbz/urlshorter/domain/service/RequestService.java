package dev.pgjbz.urlshorter.domain.service;

import java.util.Map;

public sealed interface RequestService permits RequestServiceImpl {
    void save(Map<String, String> headers);
}
