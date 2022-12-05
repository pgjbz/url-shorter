package dev.pgjbz.urlshorter.infra.controller.exception.handles;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.pgjbz.urlshorter.domain.exception.CreateResourceException;
import dev.pgjbz.urlshorter.domain.exception.ResourceNotFoundException;
import dev.pgjbz.urlshorter.domain.exception.UnknownErrorException;
import dev.pgjbz.urlshorter.infra.controller.exception.ErrorBase;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorBase> notFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildError(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UnknownErrorException.class)
    public ResponseEntity<ErrorBase> unknownError(UnknownErrorException ex, HttpServletRequest request) {
        return buildError(ex, HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(CreateResourceException.class)
    public ResponseEntity<ErrorBase> resourceError(CreateResourceException ex, HttpServletRequest request) {
        return buildError(ex, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ErrorBase> rateLimite(RequestNotPermitted ex, HttpServletRequest request) {
        return buildError(ex, HttpStatus.I_AM_A_TEAPOT, request);
    }

    private ResponseEntity<ErrorBase> buildError(RuntimeException ex, HttpStatus status, HttpServletRequest request) {
        return ResponseEntity.status(status.value()).body(
                new ErrorBase(ex.getMessage(), status.getReasonPhrase(), Instant.now(), request.getRequestURI(),
                        status.value()));
    }

}
