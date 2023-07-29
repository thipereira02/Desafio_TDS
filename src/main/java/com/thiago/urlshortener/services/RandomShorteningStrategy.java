package com.thiago.urlshortener.services;

import org.springframework.stereotype.Component;

@Component
public class RandomShorteningStrategy implements ShorteningStrategy {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 5;

    @Override
    public String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = (int)(Math.random() * ALPHABET.length());
            shortUrl.append(ALPHABET.charAt(randomIndex));
        }
        return shortUrl.toString();
    }
}
