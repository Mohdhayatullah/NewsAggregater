package org.currentaffairs.DailyBrief_AI.service;


import org.currentaffairs.DailyBrief_AI.dto.NewsResponse;
import org.currentaffairs.DailyBrief_AI.model.NewsArticle;
import org.currentaffairs.DailyBrief_AI.repository.NewsRepository;
import org.currentaffairs.DailyBrief_AI.utils.NewsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Latest news (homepage)
    public Page<NewsResponse> getLatestNews(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by("publishedAt").descending());
        return newsRepository.findAllByOrderByPublishedAtDesc(pageable).map(NewsMapper::toResponse);
    }

//     Category-wise news
    public Page<NewsResponse> getNewsByCategory(
            String category, int page, int size) {

        Pageable pageable = PageRequest.of(
                page, size, Sort.by("publishedAt").descending());

        return newsRepository.findByCategoryOrderByPublishedAtDesc(
                category.toUpperCase(), pageable).map(NewsMapper::toResponse);
    }

    // Breaking news (last 2 hours, high confidence)
    public List<NewsResponse> getBreakingNews() {
        return newsRepository.findByPublishedAtAfterAndConfidenceGreaterThan(
                LocalDateTime.now().minusHours(2), 70).stream().map(NewsMapper::toResponse).toList();
    }
}

