package dev.pgjbz.urlshorter.infra.dto.response;

public record UrlResponseDTO(
    String id, 
    String url,
    boolean expire,
    long daysToLive,
    String finalUrl
) {
    
}
