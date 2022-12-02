package dev.pgjbz.urlshorter.domain.model;

import java.time.LocalDateTime;

public record Url(
                Long id, String url,
                LocalDateTime createdAt,
                boolean expire,
                Integer ttl) {

        public Url(String url) {
                this(null, url, null, true, null);
        }

}
