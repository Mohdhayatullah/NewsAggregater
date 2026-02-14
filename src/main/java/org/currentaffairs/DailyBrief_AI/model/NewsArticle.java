package org.currentaffairs.DailyBrief_AI.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "news_article", uniqueConstraints = {
        @UniqueConstraint(columnNames = "url")
})
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 500000000)
    private String description;

    @Lob
    private String content;

    private String source;

    private String author;

    @Column(length = 5)
    private String country;

    @Column(nullable = false, unique = true)
    private String url;

    private LocalDateTime publishedAt;

    private String category;

    private Double confidence;

    private String classifiedBy;

    private LocalDateTime fetchedAt;

    // Constructors
    public NewsArticle() {
    }

    public NewsArticle(Long id, String title, String description,
                       String content, String source, String author,
                       String country, String url, LocalDateTime publishedAt,
                       String category, Double confidence, String classifiedBy,
                       LocalDateTime fetchedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.source = source;
        this.author = author;
        this.country = country;
        this.url = url;
        this.publishedAt = publishedAt;
        this.category = category;
        this.confidence = confidence;
        this.classifiedBy = classifiedBy;
        this.fetchedAt = fetchedAt;
    }
// Getters and Setters
}

