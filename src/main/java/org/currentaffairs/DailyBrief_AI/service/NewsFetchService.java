package org.currentaffairs.DailyBrief_AI.service;


import lombok.RequiredArgsConstructor;
import org.currentaffairs.DailyBrief_AI.dto.NewsApiArticle;
import org.currentaffairs.DailyBrief_AI.dto.NewsApiResponse;
import org.currentaffairs.DailyBrief_AI.model.NewsArticle;
import org.currentaffairs.DailyBrief_AI.repository.NewsRepository;
import org.currentaffairs.DailyBrief_AI.utils.TextCleaner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewsFetchService {

    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;
    private final NewsClassificationService newsClassificationService;

    private static final Logger log =
            LoggerFactory.getLogger(NewsFetchService.class);

    @Value("${newsapi.key}")
    private String apiKey;

    @Value("${newsapi.url}")
    private String apiUrl;

    // Runs every 6 hours (4 times/day)
    @Scheduled(cron = "0 0 */6 * * ?")
    public void fetchWorldwideNews() {
        log.info("Starting news fetch job");
        String url = apiUrl +
                "?q=news" +
                "&language=en" +
                "&sortBy=publishedAt" +
                "&pageSize=50" +
                "&apiKey=" + apiKey;

        NewsApiResponse response =
                restTemplate.getForObject(url, NewsApiResponse.class);

        if (response == null || response.getArticles() == null) {
            return;
        }

        for (NewsApiArticle apiArticle : response.getArticles()) {

            if (newsRepository.findByUrl(apiArticle.getUrl()).isPresent()) {
                continue;
            }

            String cleanedText = TextCleaner.clean(
                    apiArticle.getTitle() + " " +
                            apiArticle.getDescription() + " " +
                            apiArticle.getContent()
            );

            NewsArticle article = new NewsArticle();
            article.setTitle(apiArticle.getTitle());
            article.setDescription(apiArticle.getDescription());
            article.setContent(cleanedText);
            article.setSource(apiArticle.getSource() != null ? apiArticle.getSource().getName() : null);
            article.setAuthor(apiArticle.getAuthor());
            article.setUrl(apiArticle.getUrl());
            article.setPublishedAt(LocalDateTime.now());
            article.setFetchedAt(LocalDateTime.now());
            article.setCategory("UNCLASSIFIED");
            article.setConfidence(0.0);
            article.setClassifiedBy("RAW");
            newsRepository.save(article);
            log.info("Saved article: {}", apiArticle.getTitle());
        }

        log.info("News fetch completed. Starting classification");
        newsClassificationService.classifyUnprocessedNews();
        log.info("Classification completed");
    }

}

