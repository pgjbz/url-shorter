package dev.pgjbz.urlshorter.infra.configuration.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@ConfigurationProperties(prefix = "ratelimit")
public record RateLimiterProps(
    @NotNull
    @Positive
    Integer timeoutDuration,
    @NotNull
    @Positive
    Integer refreshPeriod,
    @NotNull
    @Positive
    Integer requestsPerPeriod
) {
    @ConstructorBinding
    @SuppressWarnings("all")
    public RateLimiterProps {
        //empty because use constructor binding
    }
}
