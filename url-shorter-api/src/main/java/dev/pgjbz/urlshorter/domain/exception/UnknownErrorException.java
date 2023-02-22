package dev.pgjbz.urlshorter.domain.exception;

public class UnknownErrorException extends RuntimeException {

    public UnknownErrorException(final String msg) {
        super(msg);
    }
    
}
