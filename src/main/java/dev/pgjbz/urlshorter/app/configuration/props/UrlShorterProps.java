package dev.pgjbz.urlshorter.app.configuration.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "url")
public record UrlShorterProps(
    @NotBlank
    String hostName
) {
    
    @ConstructorBinding
    @SuppressWarnings("all")
    public UrlShorterProps {
        //empty because use constructor binding
    }
}
