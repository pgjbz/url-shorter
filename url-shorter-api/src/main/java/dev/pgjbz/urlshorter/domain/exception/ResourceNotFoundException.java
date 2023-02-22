package dev.pgjbz.urlshorter.domain.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String msg) {
        super(msg);
    }
    
}
