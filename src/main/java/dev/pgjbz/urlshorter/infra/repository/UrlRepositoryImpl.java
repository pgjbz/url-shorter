package dev.pgjbz.urlshorter.infra.repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dev.pgjbz.urlshorter.domain.exception.CreateResourceException;
import dev.pgjbz.urlshorter.domain.exception.UnknownErrorException;
import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UrlRepositoryImpl implements UrlRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Url create(final Url url) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String insert = "insert into tb_url (url, ttl) values (:url, 5)";
        final SqlParameterSource paramSource = new MapSqlParameterSource(Map.of("url", url.url()));
        if (jdbcTemplate.update(insert, paramSource, keyHolder, new String[] { "id" }) > 0)
            return new Url(keyHolder.getKeyAs(Long.class), url.url(), LocalDateTime.now(), true, 5);
        final String message = "error on create resource with params: %s".formatted(url);
        log.error(message);
        throw new CreateResourceException(message);
    }

    @Override
    public Optional<Url> findById(final Long id) {
        final SqlParameterSource paramSource = new MapSqlParameterSource(Map.of("id", id));
        final String selectById = """
                    select
                    u.id,
                    u.url,
                    u.created_at,
                    u.expire,
                    u.ttl
                from
                    tb_url u
                where
                    u.id = :id
                    and u.created_at  >= (current_timestamp - make_interval(days => u.ttl))
                """;
        try {
            return Optional.of(jdbcTemplate.queryForObject(selectById, paramSource,
                    (rs, rowNum) -> new Url(rs.getLong("id"),
                            rs.getString("url"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getBoolean("expire"),
                            rs.getInt("ttl"))));
        } catch (EmptyResultDataAccessException e) {
            log.error("no resource founded with id: {}", id);
        } catch (Exception e) {
            log.error("error on select url: {}", e.getMessage(), e);
            throw new UnknownErrorException("unknown error has occurred");
        }
        return Optional.empty();
    }

}
