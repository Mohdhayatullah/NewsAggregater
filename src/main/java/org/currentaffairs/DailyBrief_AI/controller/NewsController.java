package org.currentaffairs.DailyBrief_AI.controller;


import lombok.RequiredArgsConstructor;
import org.currentaffairs.DailyBrief_AI.dto.NewsResponse;
import org.currentaffairs.DailyBrief_AI.model.NewsArticle;
import org.currentaffairs.DailyBrief_AI.service.NewsFetchService;
import org.currentaffairs.DailyBrief_AI.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
@CrossOrigin // allow frontend access
public class NewsController {

    private final NewsService newsService;
    private final NewsFetchService newsFetchService;

    // Latest news
    @GetMapping("/latest")
    public Page<NewsResponse> latestNews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return newsService.getLatestNews(page, size);
    }

    // Category-wise news
    @GetMapping("/category/{category}")
    public Page<NewsResponse> byCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return newsService.getNewsByCategory(category, page, size);
    }

    // Breaking news
    @GetMapping("/breaking")
    public List<NewsResponse> breakingNews() {
        return newsService.getBreakingNews();
    }



//    for test
    @GetMapping("/fatch")
    public void fatch(){
        newsFetchService.fetchWorldwideNews();
    }
}

