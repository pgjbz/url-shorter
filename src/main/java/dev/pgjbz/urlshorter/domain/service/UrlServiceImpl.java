package dev.pgjbz.urlshorter.domain.service;

import java.util.Objects;

import dev.pgjbz.urlshorter.domain.exception.InvalidUrlException;
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
        if(Objects.nonNull(url.url()) && !url.url().isBlank())
            return urlRepository.create(url);
        throw new InvalidUrlException("url cannot be null or empyt");
    }

    @Override
    public Url findUrlById(final Long id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("url not found"));
    }

}
