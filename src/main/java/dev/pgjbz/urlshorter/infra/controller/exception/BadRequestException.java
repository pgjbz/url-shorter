package dev.pgjbz.urlshorter.infra.controller.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String msg) {
        super(msg);
    }
    
}
