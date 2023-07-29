package com.thiago.urlshortener.units.utils;

import com.thiago.urlshortener.utils.UrlValidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlValidatorTest {
    
    @Test
    public void testValidateUrl() {
        assertTrue(UrlValidator.isValid("http://www.example.com"));
        assertTrue(UrlValidator.isValid("https://example.com"));
    }

    @Test
    public void testInvalidUrl() {
        assertFalse(UrlValidator.isValid("www.example"));
        assertFalse(UrlValidator.isValid("example.com"));
        assertFalse(UrlValidator.isValid("example"));
        assertFalse(UrlValidator.isValid("http://"));
        assertFalse(UrlValidator.isValid("http://www"));
        assertFalse(UrlValidator.isValid("http://www."));
        assertFalse(UrlValidator.isValid("http://www.example."));
        assertFalse(UrlValidator.isValid("http://www.example.c"));
        assertFalse(UrlValidator.isValid("http://www.example.com@invalid"));
    }
}
