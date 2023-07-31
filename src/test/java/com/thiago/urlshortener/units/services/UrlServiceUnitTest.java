package com.thiago.urlshortener.units.services;

import com.thiago.urlshortener.repositories.UrlRepository;
import com.thiago.urlshortener.services.ShorteningStrategy;
import com.thiago.urlshortener.services.UrlService;
import com.thiago.urlshortener.entities.UrlEntity;
import com.thiago.urlshortener.entities.UrlStatistics;
import com.thiago.urlshortener.units.services.mocks.MockUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testUpdateUrlStatistics() {
        String shortUrl = "http://encurte.com/abc12";
        UrlEntity urlEntity = new UrlEntity("http://www.example.com", shortUrl, 10, LocalDateTime.now());
        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(urlEntity);

        urlService.updateUrlStatistics(shortUrl);

        assertEquals(11, urlEntity.getClicks());
        assertNotNull(urlEntity.getLastClickAt());
        verify(urlRepository).save(urlEntity);
    }

    @Test
    public void testGetStatistics_whenEmptyList() {
        when(urlRepository.findAll()).thenReturn(new ArrayList<>());

        List<UrlStatistics> result = urlService.getStatistics();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetStatistics_whenNotEmptyList() {
        List<UrlEntity> urlEntities = new ArrayList<>();
        urlEntities.add(MockUtils.mockUrlEntity("http://encurte.com/abc12"));
        urlEntities.add(MockUtils.mockUrlEntity("http://encurte.com/def34"));
        urlEntities.add(MockUtils.mockUrlEntity("http://encurte.com/ghi56"));
        when(urlRepository.findAll()).thenReturn(urlEntities);

        List<UrlStatistics> result = urlService.getStatistics();

        assertEquals(urlEntities.size(), result.size());
    }
}
