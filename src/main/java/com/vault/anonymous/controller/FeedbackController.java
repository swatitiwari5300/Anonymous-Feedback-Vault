package com.vault.anonymous.controller;

import com.vault.anonymous.model.FeedbackRequest;
import com.vault.anonymous.service.FeedbackService;
import com.vault.anonymous.util.HashUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public Map<String, String> submitFeedback(@RequestBody FeedbackRequest request) {
        String hashedEmail = HashUtil.hash(request.getEmail());

        if (service.hasFeedback(hashedEmail)) {
            return Map.of(
                    "status", "duplicate",
                    "message", "You have already submitted feedback."
            );
        }

        service.storeFeedback(hashedEmail, request.getMessage());
        return Map.of("status", "success", "message", "Feedback submitted anonymously.");
    }

    @GetMapping("/verify")
    public Map<String, Object> verify(@RequestParam String email) {
        String hashedEmail = HashUtil.hash(email);
        boolean exists = service.hasFeedback(hashedEmail);
        String message = exists ? service.getFeedback(hashedEmail) : null;

        return Map.of(
                "submitted", exists,
                "message", message != null ? message : "No feedback found for this email."
        );
    }
}
