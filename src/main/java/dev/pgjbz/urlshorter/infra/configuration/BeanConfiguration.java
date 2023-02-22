package dev.pgjbz.urlshorter.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.pgjbz.urlshorter.domain.repository.UrlRepository;
import dev.pgjbz.urlshorter.domain.service.JsonService;
import dev.pgjbz.urlshorter.domain.service.MessageService;
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
    public RequestService requestService(final JsonService jsonService, final MessageService messageService) {
        return new RequestServiceImpl(messageService, jsonService);
    }

}
