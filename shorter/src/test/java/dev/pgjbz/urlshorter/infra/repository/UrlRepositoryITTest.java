package dev.pgjbz.urlshorter.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.jdbc.Sql;

import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class UrlRepositoryITTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    @Sql({ "/data/drop.sql", "/data/create.sql" })
    void testCreateNewUrl() {
        final var url = "https://google.com";
        var urlObject = new Url(null, url, null, true, 5);
        urlObject = urlRepository.create(urlObject);
        assertEquals(urlObject, new Url(1L, url, null, true, 5), "expected equals object");
    }

    @Test
    @Sql({ "/data/drop.sql", "/data/create.sql", "/data/insert.sql" })
    void testFindById() {
        final var url = "https://google.com";
        final var urlObject = urlRepository.findById(1L).get();
        assertEquals(urlObject, new Url(1L, url, null, true, 5), "expected equals object");
    }

}
