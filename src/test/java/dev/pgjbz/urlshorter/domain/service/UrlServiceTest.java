package dev.pgjbz.urlshorter.domain.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dev.pgjbz.urlshorter.domain.exception.InvalidUrlException;
import dev.pgjbz.urlshorter.domain.model.Url;
import dev.pgjbz.urlshorter.domain.repository.UrlRepository;

public class UrlServiceTest {

    private UrlService urlService;
    private UrlRepository urlRepository;

    @BeforeEach
    void setup() {
        urlRepository = mock(UrlRepository.class);
        urlService = new UrlServiceImpl(urlRepository);
    }

    @Test
    void testSaveUrlExpectedSuccess() {
        final var url = new Url("https://foo.com");

        assertDoesNotThrow(() -> urlService.create(url));

        verify(urlRepository).create(url);
    }

    @ParameterizedTest
    @MethodSource("invalidUrlSource")
    void testSaveUrlExpectedInvalidUrlException(Url url) {
        InvalidUrlException exception = assertThrows(InvalidUrlException.class, () -> urlService.create(url));
        assertEquals("url cannot be null or empyt", exception.getMessage());
    }

    private static Stream<Arguments> invalidUrlSource() {
        return Stream.of(
                Arguments.of(new Url(null)),
                Arguments.of(new Url("")));
    }

}
