package dev.pgjbz.urlshorter.infra.repository;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.pgjbz.urlshorter.domain.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Long urlId, String headers) {
        final String insert = "insert into tb_request(url_id, headers) values (:urlId, :headers::json)";
        Map<String, Object> values = Map.of("headers", headers, "urlId", urlId);
        try {
            log.info("inserting headers: {}", headers);
            return jdbcTemplate.update(insert, values) > 0;
        } catch (Exception e) {
            log.error("error on insert request headers: {}", e.getMessage(), e);
            throw e;
        }
    }

}
