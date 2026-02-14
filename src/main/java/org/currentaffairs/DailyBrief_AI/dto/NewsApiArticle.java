package org.currentaffairs.DailyBrief_AI.dto;


import lombok.Getter;

@Getter
public class NewsApiArticle {

    // getters only (setters optional)
    private String title;
    private String description;
    private String content;
    private String url;
    private String author;
    private String publishedAt;
    private Source source;

    public static class Source {
        private String name;

        public String getName() {
            return name;
        }
    }

}
