package com.thiago.urlshortener.util;

public class UrlValidator {
    private static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

    public static boolean isValid(String url) {
        return url.matches(URL_REGEX);
    }
}
