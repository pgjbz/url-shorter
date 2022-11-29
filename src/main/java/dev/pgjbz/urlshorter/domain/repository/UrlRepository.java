package dev.pgjbz.urlshorter.domain.repository;

import java.util.Optional;

import dev.pgjbz.urlshorter.domain.model.Url;

public interface UrlRepository {
    
    Url create(final Url url);
    Optional<Url> findById(final Long id);

}
