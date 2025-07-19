package com.vault.anonymous.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class FeedbackService {
    private final ConcurrentHashMap<String, String> feedbackStore = new ConcurrentHashMap<>();

    public boolean hasFeedback(String hashedEmail) {
        return feedbackStore.containsKey(hashedEmail);
    }

    public void storeFeedback(String hashedEmail, String message) {
        feedbackStore.put(hashedEmail, message);
    }

    public String getFeedback(String hashedEmail) {
        return feedbackStore.get(hashedEmail);
    }
}
