package com.thiago.urlshortener.units.services.mocks;

import com.thiago.urlshortener.entities.UrlEntity;

public class MockUtils {
    
    public static UrlEntity mockUrlEntity(String shortUrl) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl("http://www.example.com");
        urlEntity.setShortUrl(shortUrl);
        urlEntity.setClicks(0);
        urlEntity.setLastClickAt(null);
        return urlEntity;
    }
}
