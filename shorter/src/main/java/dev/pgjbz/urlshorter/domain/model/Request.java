package dev.pgjbz.urlshorter.domain.model;

import java.util.Map;

public record Request(Map<String, String> headers, Long urlId) {

}
