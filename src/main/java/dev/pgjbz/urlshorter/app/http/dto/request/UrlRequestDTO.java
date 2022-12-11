package dev.pgjbz.urlshorter.app.http.dto.request;

import org.hibernate.validator.constraints.URL;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(name = "Request", description = "Url to short")
public record UrlRequestDTO(@NotBlank @URL String url) {

}
