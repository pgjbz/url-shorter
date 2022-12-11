package dev.pgjbz.urlshorter.app.http.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Response", description = "Url to short")
public record UrlResponseDTO(
    String id, 
    String url,
    boolean expire,
    long daysToLive,
    String finalUrl
) {
    
}
