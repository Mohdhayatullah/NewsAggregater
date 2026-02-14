package org.currentaffairs.DailyBrief_AI.utils;

import java.util.Map;

public class TfIdfUtil {

    // Approximate IDF values (manually tuned)
    private static final Map<String, Double> IDF = Map.ofEntries(
            Map.entry("ai", 2.5),
            Map.entry("election", 2.8),
            Map.entry("stock", 2.2),
            Map.entry("cricket", 3.0),
            Map.entry("vaccine", 3.1),
            Map.entry("government", 2.0),
            Map.entry("software", 2.3),
            Map.entry("market", 2.1)
    );

    public static double score(String text, String keyword) {
        int count = countOccurrences(text, keyword);
        if (count == 0) return 0.0;

        double tf = (double) count / text.split(" ").length;
        double idf = IDF.getOrDefault(keyword, 1.5);

        return tf * idf;
    }

    private static int countOccurrences(String text, String keyword) {
        int count = 0;
        for (String token : text.split(" ")) {
            if (token.equals(keyword)) {
                count++;
            }
        }
        return count;
    }
}

