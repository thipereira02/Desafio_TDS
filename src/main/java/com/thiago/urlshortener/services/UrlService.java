package com.thiago.urlshortener.services;

import com.thiago.urlshortener.entities.UrlEntity;
import com.thiago.urlshortener.repositories.UrlRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private static final String BASE_URL = "http://encurte.com/";

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ShorteningStrategy shorteningStrategy;

    public String shortenUrl(String originalUrl) {
        String shortUrl = BASE_URL + shorteningStrategy.generateShortUrl();

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(originalUrl);
        urlEntity.setShortUrl(shortUrl);
        urlEntity.setClicks(0);
        urlEntity.setLastClickAt(null);

        urlRepository.save(urlEntity);

        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        return (urlEntity != null) ? urlEntity.getOriginalUrl() : null;
    }

    public void updateUrlStatistics(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        if (urlEntity != null) {
            urlEntity.setClicks(urlEntity.getClicks() + 1);
            urlEntity.setLastClickAt(LocalDateTime.now());
            urlRepository.save(urlEntity);
        }
    }
}
