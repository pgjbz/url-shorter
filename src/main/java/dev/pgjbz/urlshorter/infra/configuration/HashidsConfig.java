package dev.pgjbz.urlshorter.infra.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hashids")
public record HashidsConfig(String salt) {
    
}
