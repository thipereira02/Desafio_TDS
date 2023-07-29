package com.thiago.urlshortener.util;

public class UrlValidator {
    private static final String URL_REGEX = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValid(String url) {
        return url.matches(URL_REGEX);
    }
}
