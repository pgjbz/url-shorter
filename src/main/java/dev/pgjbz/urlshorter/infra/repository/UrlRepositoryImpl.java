package dev.pgjbz.urlshorter.infra.repository;

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
        final String insert = "insert into tb_url (url) values (:url)";
        final SqlParameterSource paramSource = new MapSqlParameterSource(Map.of("url", url.url()));
        if (jdbcTemplate.update(insert, paramSource, keyHolder, new String[] { "id" }) > 0)
            return new Url(keyHolder.getKeyAs(Long.class), url.url());
        final String message = "error on create resource with params: %s".formatted(url);
        log.error(message);
        throw new CreateResourceException(message);
    }

    @Override
    public Optional<Url> findById(final Long id) {
        final SqlParameterSource paramSource = new MapSqlParameterSource(Map.of("id", id));
        final String selectById = """
                select
                    id,
                    url
                from
                    tb_url
                where
                    id = :id
                """;
        try {
            return Optional.of(jdbcTemplate.queryForObject(selectById, paramSource,
                    (rs, rowNum) -> new Url(rs.getLong("id"), rs.getString("url"))));
        } catch (EmptyResultDataAccessException e) {
            log.error("no resource founded with id: {}", id);
        } catch (Exception e) {
            throw new UnknownErrorException(e);
        }
        return Optional.empty();
    }

}
