package com.thiago.urlshortener.entities;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private long clicks;
    private LocalDateTime lastClickAt;
}
