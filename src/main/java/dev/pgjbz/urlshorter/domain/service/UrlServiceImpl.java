package dev.pgjbz.urlshorter.domain.service;

import dev.pgjbz.urlshorter.domain.exception.ResourceNotFoundException;
import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;

public final class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url create(final Url url) {
        return urlRepository.create(url);
    }

    @Override
    public Url findUrlById(final Long id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("url not found"));
    }

}
