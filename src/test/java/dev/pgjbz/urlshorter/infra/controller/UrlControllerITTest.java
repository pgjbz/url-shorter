package dev.pgjbz.urlshorter.infra.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import dev.pgjbz.urlshorter.infra.dto.request.UrlRequestDTO;
import dev.pgjbz.urlshorter.infra.dto.response.UrlResponseDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlControllerITTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @SuppressWarnings("all")
    @Sql({ "/data/drop.sql", "/data/create.sql"})
    void testCreateUrlExpectedCreateShorthenUrl() {
        final var url = "https://google.com";
        final var urlRequest = new UrlRequestDTO(url);
        final var urlResponse = restTemplate.postForEntity("/urls", urlRequest, UrlResponseDTO.class);
        final var urlResponseBody = urlResponse.getBody();
        assertEquals(HttpStatus.CREATED, urlResponse.getStatusCode(), "expected CREATED http code");
        assertEquals("Gp", urlResponseBody.id(), "id have to be equals to 'Gp', because cannot be incremental id");
        assertEquals(url, urlResponseBody.url());
    }

    @Test
    @SuppressWarnings("all")
    @Sql({ "/data/drop.sql", "/data/create.sql", "/data/insert.sql" })
    void testFindUrl() {
        final var path = "/Gp";
        final var response = restTemplate.getForEntity(path, String.class);
        assertEquals(HttpStatus.FOUND, response.getStatusCode(), "expected CREATED http code");
    }

}
