package com.thiago.urlshortener.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "urls_statistics")
public class UrlStatistics {
    @Id
    private String id;
    private String shortUrl;
    private int clicks;
    private LocalDateTime lastClickAt;
}
