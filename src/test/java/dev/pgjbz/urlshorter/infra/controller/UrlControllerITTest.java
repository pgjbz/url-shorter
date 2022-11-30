package dev.pgjbz.urlshorter.infra.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import dev.pgjbz.urlshorter.infra.dto.request.UrlRequestDTO;
import dev.pgjbz.urlshorter.infra.dto.response.UrlResponseDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlControllerITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @SuppressWarnings("all")
    void testCreateUrlExpectedCreateShorthenUrl() {
        final var url = "https://google.com";
        final var urlRequest = new UrlRequestDTO(url);
        final var urlResponse = restTemplate.postForEntity("/urls", urlRequest, UrlResponseDTO.class);
        final var urlResponseBody = urlResponse.getBody();
        assertEquals(HttpStatus.CREATED, urlResponse.getStatusCode(), "expected CREATED http code");
        assertNotEquals("1", urlResponseBody.id(), "id cannot be equals '1'");
        assertEquals(url, urlResponseBody.url());
    }
    
}
