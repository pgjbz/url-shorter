package dev.pgjbz.urlshorter.infra.repository;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import dev.pgjbz.urlshorter.domain.repository.RequestRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RequestRepositoryITTest {
    
    @Autowired
    private RequestRepository requestRepository;

    @Test
    @Sql({ "/data/drop.sql", "/data/create.sql", "/data/insert.sql" })
    void testCreateRequestRegisterExpectedSuccess() {
        final var urlId = 1L;
        final var headersString = "{\"foo\":\"bar\"}";
        
        assertDoesNotThrow(() -> requestRepository.save(urlId, headersString));
    }

    @Test
    @Sql({ "/data/drop.sql", "/data/create.sql" })
    void testCreateRequestRegisterWillFail() {
        final var urlId = 1L;
        final var headersString = "{\"foo\":\"bar\"}";
        
        assertThrows(DataIntegrityViolationException.class, () -> requestRepository.save(urlId, headersString));
    }

}
