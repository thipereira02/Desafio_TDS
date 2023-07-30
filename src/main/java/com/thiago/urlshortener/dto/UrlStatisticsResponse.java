package com.thiago.urlshortener.dto;

import com.thiago.urlshortener.entities.UrlStatistics;

import java.util.List;

public class UrlStatisticsResponse {
    private List<UrlStatistics> urlStatisticsList;

    public UrlStatisticsResponse(List<UrlStatistics> urlStatisticsList) {
        this.urlStatisticsList = urlStatisticsList;
    }

    public List<UrlStatistics> getUrlStatisticsList() {
        return urlStatisticsList;
    }
}
