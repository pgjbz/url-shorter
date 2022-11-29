package dev.pgjbz.urlshorter.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;

import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlRepositoryITTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    @Sql({ "/data/clean.sql", "/data/create.sql" })
    void testCreateNewUrl() {
        final var url = "https://google.com";
        var urlObject = new Url(null, url);
        urlObject = urlRepository.create(urlObject);
        assertEquals(urlObject, new Url(1L, url), "expected equals object");
    }

    @Test
    @Sql({ "/data/clean.sql", "/data/create.sql", "/data/insert.sql" })
    void testFindById() {
        final var url = "https://google.com";
        final var urlObject = urlRepository.findById(1L).get();
        assertEquals(urlObject, new Url(1L, url), "expected equals object");
    }

}
