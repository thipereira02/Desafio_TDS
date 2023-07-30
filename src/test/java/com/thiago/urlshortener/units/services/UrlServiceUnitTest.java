package com.thiago.urlshortener.units.services;

import com.thiago.urlshortener.repositories.UrlRepository;
import com.thiago.urlshortener.services.ShorteningStrategy;
import com.thiago.urlshortener.services.UrlService;
import com.thiago.urlshortener.entities.UrlEntity;
import com.thiago.urlshortener.units.services.mocks.MockUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlServiceUnitTest {
    
    @Mock
    private UrlRepository urlRepository;

    @Mock
    private ShorteningStrategy shorteningStrategy;

    @InjectMocks
    private UrlService urlService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenUrl_whenUrlIsValid() {
        String originalUrl = "http://www.example.com";
        String shortUrl = "http://encurte.com/abc12";

        when(shorteningStrategy.generateShortUrl()).thenReturn("abc12");
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(MockUtils.mockUrlEntity(shortUrl));

        String result = urlService.shortenUrl(originalUrl);

        assertEquals(shortUrl, result);
    }

    @Test
    public void testGetOriginalUrl_whenUrlExists() {
        String shortUrl = "http://encurte.com/abc12";
        UrlEntity urlEntity = MockUtils.mockUrlEntity(shortUrl);

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(urlEntity);

        String result = urlService.getOriginalUrl(shortUrl);

        assertEquals(urlEntity.getOriginalUrl(), result);
    }

    @Test
    public void testGetOriginalUrl_whenUrlDoesNotExist() {
        String shortUrl = "http://encurte.com/abc12";

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(null);

        String result = urlService.getOriginalUrl(shortUrl);

        assertEquals(null, result);
    }

    @Test
    public void testUpdateUrlStatistics_whenUrlExists() {
        String shortUrl = "http://encurte.com/abc12";
        UrlEntity urlEntity = MockUtils.mockUrlEntity(shortUrl);

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(urlEntity);
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(urlEntity);

        urlService.updateUrlStatistics(shortUrl);

        assertEquals(1, urlEntity.getClicks());
    }
}
