package com.thiago.urlshortener.units.services;

import com.thiago.urlshortener.repositories.UrlRepository;
import com.thiago.urlshortener.services.ShorteningStrategy;
import com.thiago.urlshortener.services.UrlService;
import com.thiago.urlshortener.entities.UrlEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(mockUrlEntity(shortUrl));

        String result = urlService.shortenUrl(originalUrl);

        assertEquals(shortUrl, result);

    }

    private UrlEntity mockUrlEntity(String shortUrl) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl("http://www.example.com");
        urlEntity.setShortUrl(shortUrl);
        urlEntity.setClicks(0);
        urlEntity.setLastClickAt(null);
        return urlEntity;
    }
}
