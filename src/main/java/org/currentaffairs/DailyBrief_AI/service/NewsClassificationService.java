package org.currentaffairs.DailyBrief_AI.service;

import org.currentaffairs.DailyBrief_AI.model.NewsArticle;
import org.currentaffairs.DailyBrief_AI.repository.NewsRepository;
import org.currentaffairs.DailyBrief_AI.utils.TfIdfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsClassificationService {

    private final NewsRepository newsRepository;
    private static final Logger log =
            LoggerFactory.getLogger(NewsClassificationService.class);


    public NewsClassificationService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Keyword dictionary (AI knowledge base)
    private static final Map<String, List<String>> CATEGORY_KEYWORDS = new HashMap<>();

    static {
        CATEGORY_KEYWORDS.put("TECH", List.of(
                "ai", "artificial intelligence", "software", "technology",
                "startup", "google", "microsoft", "openai", "chip"
        ));

        CATEGORY_KEYWORDS.put("POLITICS", List.of(
                "election", "government", "minister", "policy",
                "parliament", "president", "prime minister"
        ));

        CATEGORY_KEYWORDS.put("BUSINESS", List.of(
                "market", "stock", "company", "revenue",
                "economy", "finance", "investment"
        ));

        CATEGORY_KEYWORDS.put("SPORTS", List.of(
                "match", "tournament", "goal", "cricket",
                "football", "olympics"
        ));

        CATEGORY_KEYWORDS.put("HEALTH", List.of(
                "health", "disease", "virus", "vaccine",
                "hospital", "medical"
        ));
    }

    public void classifyUnprocessedNews() {

        // Fetch articles not yet classified
        List<NewsArticle> articles =
                newsRepository.findAll()
                        .stream()
                        .filter(a -> "UNCLASSIFIED".equals(a.getCategory()))
                        .toList();

        for (NewsArticle article : articles) {

            String text = article.getContent();

            Map<String, Double> scores = new HashMap<>();

            for (var entry : CATEGORY_KEYWORDS.entrySet()) {
//                int score = 0;
//
//                for (String keyword : entry.getValue()) {
//                    if (text.contains(keyword)) {
//                        score++;
//                    }
//                }
//                scores.put(entry.getKey(), score);
                double score = 0.0;

                for (String keyword : entry.getValue()) {
                    score += TfIdfUtil.score(text, keyword);
                }
                scores.put(entry.getKey(), score);

            }

            // Find best category
            String bestCategory = "GENERAL";
            double maxScore = 0.0;

            for (var entry : scores.entrySet()) {
                if (entry.getValue() > maxScore) {
                    maxScore = entry.getValue();
                    bestCategory = entry.getKey();
                }
            }
            
            // Confidence calculation
            int totalKeywords =
                    CATEGORY_KEYWORDS.getOrDefault(bestCategory, List.of()).size();

            double confidence = totalKeywords == 0
                    ? 0
                    : (maxScore * 100) / totalKeywords;

            // Update article
            article.setCategory(bestCategory);
            article.setConfidence(confidence);
            article.setClassifiedBy("KEYWORD");
            log.info("Classified article [{}] as {} (confidence {}%)",
                    article.getTitle(),
                    bestCategory,
                    confidence);

            newsRepository.save(article);
        }
    }
}
