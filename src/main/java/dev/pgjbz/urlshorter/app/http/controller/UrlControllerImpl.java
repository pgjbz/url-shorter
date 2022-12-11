package dev.pgjbz.urlshorter.app.http.controller;

import java.io.IOException;
import java.util.Map;

import org.hashids.Hashids;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pgjbz.urlshorter.app.http.dto.request.UrlRequestDTO;
import dev.pgjbz.urlshorter.app.http.dto.response.UrlResponseDTO;
import dev.pgjbz.urlshorter.domain.exception.ResourceNotFoundException;
import dev.pgjbz.urlshorter.domain.model.Request;
import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.service.RequestService;
import dev.pgjbz.urlshorter.domain.service.UrlService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
@Tag(name = "Url", description = "Operation to url shorter")
public class UrlControllerImpl implements UrlController {

    private final UrlService urlService;
    private final Hashids hashids;
    private final RequestService requestService;
    private final RateLimiterRegistry rateLimiterRegistry;

    public void findUrl(@PathVariable(value = "id") final String id, final HttpServletResponse response,
            @RequestHeader final Map<String, String> headers) throws IOException {
        final long idDecoded = decodeId(id);
        requestService.save(new Request(headers, idDecoded));
        final Url url = urlService.findUrlById(idDecoded);
        response.sendRedirect(url.url());
    }

    public ResponseEntity<UrlResponseDTO> create(@RequestBody final UrlRequestDTO url,
            @RequestHeader final Map<String, String> headers) {
        final RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("create-url");
        final Url urlModel = rateLimiter.executeSupplier(() -> urlService.create(new Url(url.url())));
        final String encodedId = encodeId(urlModel.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new UrlResponseDTO(encodedId, urlModel.url(), urlModel.expire(), urlModel.ttl(),
                        buildFinalUrl(headers, encodedId)));
    }

    private String encodeId(Long id) {
        return hashids.encode(id);
    }

    private String buildFinalUrl(Map<String, String> headers, String encodedId) {
        if (headers.containsKey("host")) {
            return headers.get("host") + "/%s".formatted(encodedId);
        }
        return "localhost:8080/%s".formatted(encodedId);
    }

    private long decodeId(final String id) {
        final long[] decodedeIds = hashids.decode(id);
        if (decodedeIds.length != 1)
            throw new ResourceNotFoundException("url with id %s not found".formatted(id));
        return decodedeIds[0];
    }
}
