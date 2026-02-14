package org.currentaffairs.DailyBrief_AI.utils;


public class TextCleaner {

    // Private constructor to prevent object creation
    private TextCleaner() {
    }

    /**
     * Cleans raw news text before classification
     */
    public static String clean(String text) {

        if (text == null) {
            return "";
        }

        // Convert to lowercase
        text = text.toLowerCase();

        // Remove HTML tags
        text = text.replaceAll("<[^>]*>", " ");

        // Remove URLs
        text = text.replaceAll("http\\S+|www\\S+", " ");

        // Remove special characters (keep letters and spaces)
        text = text.replaceAll("[^a-zA-Z ]", " ");

        // Remove extra spaces
        text = text.replaceAll("\\s+", " ").trim();

        return text;
    }
}

