package dev.pgjbz.urlshorter.app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "hashids")
public record HashidsProps(
        @NotNull String salt) {

    @SuppressWarnings("all")
    @ConstructorBinding
    public HashidsProps {

    }
}
