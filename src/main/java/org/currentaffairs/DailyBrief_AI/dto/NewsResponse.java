package org.currentaffairs.DailyBrief_AI.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NewsResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Double confidence;
    private String source;
    private String author;
    private String url;
    private LocalDateTime publishedAt;
}
