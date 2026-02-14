package org.currentaffairs.DailyBrief_AI.utils;

import org.currentaffairs.DailyBrief_AI.dto.NewsResponse;
import org.currentaffairs.DailyBrief_AI.model.NewsArticle;

public class NewsMapper {

    public static NewsResponse toResponse(NewsArticle article) {
        return NewsResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .category(article.getCategory())
                .confidence(article.getConfidence())
                .source(article.getSource())
                .url(article.getUrl())
                .publishedAt(article.getPublishedAt())
                .author(article.getAuthor())
                .build();
    }
}
