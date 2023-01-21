package dev.pgjbz.urlshorter.app.configuration;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashIdsConfig {
    @Bean
    public Hashids hashids(final HashidsProps hashidsConfig) {
        return new Hashids(hashidsConfig.salt());
    }
}
