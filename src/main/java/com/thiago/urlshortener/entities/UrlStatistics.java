package com.thiago.urlshortener.entities;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "urls_statistics")
public class UrlStatistics {
    private String shortUrl;
    private int clicks;
    private LocalDateTime lastClickAt;

    public UrlStatistics(String shortUrl, int clicks, LocalDateTime lastClickAt) {
        this.shortUrl = shortUrl;
        this.clicks = clicks;
        this.lastClickAt = lastClickAt;
    }
}
