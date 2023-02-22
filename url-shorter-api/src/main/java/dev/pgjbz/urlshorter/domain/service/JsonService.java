package dev.pgjbz.urlshorter.domain.service;

import java.util.Optional;

public interface JsonService {
    <T> Optional<String> toJsonString(T obj); 
}
