package com.pgjbz.urlrequestconsumer.domain.service;

import java.util.Optional;

public interface JsonService {
    <T> Optional<String> serialize(T input);
    <T> Optional<T> deserialize(String json, Class<T> clazz);
}
