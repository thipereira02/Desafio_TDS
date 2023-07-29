package com.thiago.urlshortener.services;

import com.thiago.urlshortener.entities.UrlEntity;
import com.thiago.urlshortener.repositories.UrlRepository;

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
}
