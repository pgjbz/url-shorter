package dev.pgjbz.urlshorter.app.configuration;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.pgjbz.urlshorter.app.configuration.props.RateLimiterProps;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

@Configuration
public class ResilienceRateLimiteConfig {

    private final RateLimiterProps rateLimiterProps;

    public ResilienceRateLimiteConfig(RateLimiterProps rateLimiterProps) {
        this.rateLimiterProps = rateLimiterProps;
    }

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(rateLimiterProps.timeoutDuration()))
                .limitRefreshPeriod(Duration.ofSeconds(rateLimiterProps.refreshPeriod()))
                .limitForPeriod(rateLimiterProps.requestsPerPeriod())
                .build();
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry(RateLimiterConfig rateLimiterConfig) {
        return RateLimiterRegistry.of(rateLimiterConfig);
    }
}
