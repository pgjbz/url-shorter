package dev.pgjbz.urlshorter.domain.service;

import dev.pgjbz.urlshorter.domain.model.Url;

public sealed interface UrlService permits UrlServiceImpl {
    Url create(final Url url);
    Url findUrlById(final Long id);
}
