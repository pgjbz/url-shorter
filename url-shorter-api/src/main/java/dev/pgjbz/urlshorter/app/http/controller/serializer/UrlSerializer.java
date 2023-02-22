package dev.pgjbz.urlshorter.app.http.controller.serializer;

import java.io.IOException;

import org.hashids.Hashids;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import dev.pgjbz.urlshorter.app.configuration.props.UrlShorterProps;
import dev.pgjbz.urlshorter.app.http.dto.response.UrlResponseDTO;
import dev.pgjbz.urlshorter.domain.model.Url;
import lombok.RequiredArgsConstructor;

@JsonComponent
@RequiredArgsConstructor
public class UrlSerializer extends JsonSerializer<Url> {

    private final Hashids hashids;
    private final UrlShorterProps urlShorterProps;
    
    @Override
    public void serialize(Url urlModel, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        final String encodedId = encodeId(urlModel.id());
        final String finalUrl = buildFinalUrl(encodedId);
        final var urlResponseDTO = new UrlResponseDTO(encodedId, urlModel.url(), urlModel.expire(), urlModel.ttl(), finalUrl);
        gen.writeObject(urlResponseDTO);
    }
    

    private String encodeId(Long id) {
        return hashids.encode(id);
    }

    private String buildFinalUrl(String encodedId) {
        return "%s/%s".formatted(urlShorterProps.hostName(), encodedId);
    }


}
