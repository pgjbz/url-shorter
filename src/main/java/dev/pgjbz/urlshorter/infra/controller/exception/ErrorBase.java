package dev.pgjbz.urlshorter.infra.controller.exception;

import java.time.Instant;

public record ErrorBase(
    String message,
    String error,
    Instant instant,
    String path,
    int status
) {
    
}
