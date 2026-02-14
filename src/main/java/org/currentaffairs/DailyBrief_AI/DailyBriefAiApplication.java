package org.currentaffairs.DailyBrief_AI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyBriefAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyBriefAiApplication.class, args);
	}

}
