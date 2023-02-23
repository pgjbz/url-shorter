package com.pgjbz.urlrequestconsumer.infra.repository;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pgjbz.urlrequestconsumer.domain.model.Request;
import com.pgjbz.urlrequestconsumer.domain.repository.RequestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(Request request) {
        final String insert = """
                insert into requests.tb_requests(
                    url_id,
                    headers
                ) values (
                    :urlId,
                    :headers::json
                )
                """;
        final Map<String, String> headers = request.headers();
        final Map<String, Object> values = Map.of("headers", headers, "urlId", request.urlId());
        try {
            log.info("inserting headers: {}", headers);
            jdbcTemplate.update(insert, values);
        } catch (Exception e) {
            log.error("error on insert request headers: {}", e.getMessage(), e);
            throw e;
        }
    }


}
