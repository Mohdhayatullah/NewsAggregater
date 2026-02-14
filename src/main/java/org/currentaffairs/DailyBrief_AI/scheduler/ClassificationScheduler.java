package org.currentaffairs.DailyBrief_AI.scheduler;


import org.currentaffairs.DailyBrief_AI.service.NewsClassificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClassificationScheduler {

    private final NewsClassificationService classificationService;

    public ClassificationScheduler(NewsClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    // Runs every 30 minutes
//    @Scheduled(cron = "0 */30 * * * ?")
    public void runClassification() {
        classificationService.classifyUnprocessedNews();
    }
}

