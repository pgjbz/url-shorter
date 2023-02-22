package dev.pgjbz.urlshorter.app.http.controller.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String msg) {
        super(msg);
    }
    
}
