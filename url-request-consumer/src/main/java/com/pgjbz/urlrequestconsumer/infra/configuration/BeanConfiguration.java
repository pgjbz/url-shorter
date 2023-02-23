package com.pgjbz.urlrequestconsumer.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pgjbz.urlrequestconsumer.domain.repository.RequestRepository;
import com.pgjbz.urlrequestconsumer.domain.service.JsonService;
import com.pgjbz.urlrequestconsumer.domain.service.RequestService;
import com.pgjbz.urlrequestconsumer.domain.service.RequestServiceImpl;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public RequestService requestService(final RequestRepository requestRepository, final JsonService jsonService) {
        return new RequestServiceImpl(requestRepository, jsonService);
    }
    
}
