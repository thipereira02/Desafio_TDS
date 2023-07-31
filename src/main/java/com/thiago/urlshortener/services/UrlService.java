package com.thiago.urlshortener.services;

import com.thiago.urlshortener.entities.UrlEntity;
import com.thiago.urlshortener.entities.UrlStatistics;
import com.thiago.urlshortener.repositories.UrlRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getOriginalUrl(String shortenUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortenUrl);
        return (urlEntity != null) ? urlEntity.getOriginalUrl() : null;
    }

    public void updateUrlStatistics(String shortenUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortenUrl);
        if (urlEntity != null) {
            urlEntity.setClicks(urlEntity.getClicks() + 1);
            urlEntity.setLastClickAt(LocalDateTime.now());
            urlRepository.save(urlEntity);
        }
    }

    public List<UrlStatistics> getStatistics() {
        return urlRepository.findAll().stream()
            .map(urlEntity -> new UrlStatistics(
                    urlEntity.getShortUrl(),
                    urlEntity.getClicks(),
                    urlEntity.getLastClickAt()
            ))
            .collect(Collectors.toList());
    }
}
