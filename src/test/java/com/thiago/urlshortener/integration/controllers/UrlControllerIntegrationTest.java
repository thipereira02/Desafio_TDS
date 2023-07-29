package com.thiago.urlshortener.integration.controllers;

import com.thiago.urlshortener.services.UrlService;
import com.thiago.urlshortener.controllers.UrlController;
import com.thiago.urlshortener.dto.UrlRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlControllerIntegrationTest {

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @Test
    public void testShortenUrl_whenValidUrl() {
        String originalUrl = "http://www.example.com";
        UrlRequest request = new UrlRequest();
        request.setOriginalUrl(originalUrl);
        String shortUrl = "http://encurte.com/abc12";

        when(urlService.shortenUrl(originalUrl)).thenReturn(shortUrl);

        ResponseEntity<String> response = urlController.shortenUrl(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shortUrl, response.getBody());
    }

    @Test
    public void testShortenUrl_whenInvalidUrl() {
        String originalUrl = "invalid-url";
        UrlRequest request = new UrlRequest();
        request.setOriginalUrl(originalUrl);

        ResponseEntity<String> response = urlController.shortenUrl(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("URL inválida", response.getBody());
    }
}
