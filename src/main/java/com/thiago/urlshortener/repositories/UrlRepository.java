package com.thiago.urlshortener.repositories;

import com.thiago.urlshortener.entities.UrlEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    UrlEntity findByShortUrl(String shortUrl);
}
