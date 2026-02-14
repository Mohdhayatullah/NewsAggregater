package org.currentaffairs.DailyBrief_AI.dto;



import lombok.Data;

import java.util.List;

@Data
public class NewsApiResponse {

    private String status;
    private int totalResults;

    private List<NewsApiArticle> articles;

}
