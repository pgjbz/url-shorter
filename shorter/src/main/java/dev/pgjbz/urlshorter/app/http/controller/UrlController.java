package dev.pgjbz.urlshorter.app.http.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.pgjbz.urlshorter.app.http.dto.request.UrlRequestDTO;
import dev.pgjbz.urlshorter.domain.model.Url;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface UrlController {
    @PostMapping(value = "/urls")
    @Operation(description = "create new short url", responses = {
            @ApiResponse(responseCode = "201", description = "create new url", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "invalid url schema", content = {
                    @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            {
                                "message": "Bad request",
                                "error": "Bad request",
                                "instant": "2022-01-01T00:00:00",
                                "path": "/urls",
                                "status": "400"
                            }
                            """)) }),
            @ApiResponse(responseCode = "429", description = "exceed create limit in interval", useReturnTypeSchema = false, content = {
                    @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            {
                                "message": "Too many requests",
                                "error": "Too many requests",
                                "instant": "2022-01-01T00:00:00",
                                "path": "/urls",
                                "status": "429"
                            }
                            """)) }),
            @ApiResponse(responseCode = "422", description = "fail to save url", useReturnTypeSchema = false, content = {
                    @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            {
                                "message": "Unprocessable entity",
                                "error": "Unprocessable entity",
                                "instant": "2022-01-01T00:00:00",
                                "path": "/urls",
                                "status": "422"
                            }
                            """)) })
    })

    ResponseEntity<Url> create(@RequestBody final UrlRequestDTO url,
            @RequestHeader final Map<String, String> headers);

    @GetMapping(value = "/{id}")
    @Operation(description = "find url by id", responses = {
            @ApiResponse(responseCode = "302", description = "found url and redirect"),
            @ApiResponse(responseCode = "404", description = "url not found") })
    void findUrl(@PathVariable(value = "id") final String id, final HttpServletResponse response,
            @RequestHeader final Map<String, String> headers) throws IOException;
}
