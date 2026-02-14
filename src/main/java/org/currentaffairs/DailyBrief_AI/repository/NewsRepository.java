package org.currentaffairs.DailyBrief_AI.repository;



import org.currentaffairs.DailyBrief_AI.model.NewsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
    Optional<NewsArticle> findByUrl(String url);

    Page<NewsArticle> findByCategoryOrderByPublishedAtDesc(
            String category, Pageable pageable);

    Page<NewsArticle> findAllByOrderByPublishedAtDesc(Pageable pageable);

    List<NewsArticle> findByPublishedAtAfterAndConfidenceGreaterThan(
            LocalDateTime time, Integer confidence);
}

