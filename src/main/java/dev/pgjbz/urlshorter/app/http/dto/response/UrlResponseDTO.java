package dev.pgjbz.urlshorter.app.http.dto.response;

public record UrlResponseDTO(
    String id, 
    String url,
    boolean expire,
    long daysToLive,
    String finalUrl
) {
    
}
