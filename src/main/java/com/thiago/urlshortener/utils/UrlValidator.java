package com.thiago.urlshortener.utils;

import java.util.regex.Pattern;

public class UrlValidator {
    private static final String URL_REGEX = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValid(String url) {
        return URL_PATTERN.matcher(url).matches();
    }
}
