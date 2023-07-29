package com.thiago.urlshortener.controllers;

import com.thiago.urlshortener.services.UrlService;
import com.thiago.urlshortener.utils.UrlValidator;
import com.thiago.urlshortener.dto.UrlRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/encurtar")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        String originalUrl = request.getOriginalUrl();
        if (!UrlValidator.isValid(originalUrl)) {
            return ResponseEntity.badRequest().body("URL inválida");
        }

        return ResponseEntity.ok(urlService.shortenUrl(originalUrl));
    }
}
