package dev.pgjbz.urlshorter.infra.configuration;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.pgjbz.urlshorter.domain.repository.RequestRepository;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;
import dev.pgjbz.urlshorter.domain.service.JsonService;
import dev.pgjbz.urlshorter.domain.service.RequestService;
import dev.pgjbz.urlshorter.domain.service.RequestServiceImpl;
import dev.pgjbz.urlshorter.domain.service.UrlService;
import dev.pgjbz.urlshorter.domain.service.UrlServiceImpl;

@Configuration
public class BeanConfiguration {

    
    @Bean
    public UrlService urlService(final UrlRepository urlRepository) {
        return new UrlServiceImpl(urlRepository);
    }

    @Bean
    public Hashids hashids(final HashidsConfig hashidsConfig) {
        return new Hashids(hashidsConfig.salt());
    }

    @Bean
    public RequestService requestService(JsonService jsonService, RequestRepository requestRepository) {
        return new RequestServiceImpl(requestRepository, jsonService);
    }

}
